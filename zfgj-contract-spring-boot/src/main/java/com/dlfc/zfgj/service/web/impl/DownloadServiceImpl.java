package com.dlfc.zfgj.service.web.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.FileDownload;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.common.pdf.pdf.PdfDocumentGenerator;
import com.dlfc.zfgj.common.pdf.utils.ResourceLoader;
import com.dlfc.zfgj.convertor.web.DownloadInitConvertor;
import com.dlfc.zfgj.convertor.web.PDFContractConvertor;
import com.dlfc.zfgj.dto.web.ContractDownloadInitDTO;
import com.dlfc.zfgj.dto.web.PDFConContractDTO;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.enums.InfoAttFileTypeEnum;
import com.dlfc.zfgj.enums.SettlementCycleEnum;
import com.dlfc.zfgj.exception.DocumentGeneratingException;
import com.dlfc.zfgj.mapper.ConContractMapper;
import com.dlfc.zfgj.service.web.DownloadService;
import com.dlfc.zfgj.service.web.SysInfoAttService;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by K on 2017/3/13.
 */

@Service
@Transactional
@Slf4j
public class DownloadServiceImpl implements DownloadService {
    private final static Logger logger = Logger.getLogger(DownloadServiceImpl.class);
    // 租金分期
    private static final String BY_STAGES = "01";
    // 静态变量合同
    private static final String CONTRACT = "合同";
    // 服务器地址
    private static String SERVER_PATH = "https://lesseezfgj.housecenter.cn";

    @Autowired
    private ConContractMapper conContractMapper;
    @Autowired
    private SysInfoAttService sysInfoAttServiceImpl;
    @Autowired
    private PDFContractConvertor pdfContractConvertor;
    @Autowired
    private DownloadInitConvertor downloadInitConvertor;

    /**
     * PDF下载
     *
     * @param contractId
     * @param request
     * @param response
     * @param user
     * @throws ApplicationException
     */
    @Override
    public void download(String contractId,
                         HttpServletRequest request,
                         HttpServletResponse response, UsrUser user) throws Exception {
//        String serverPath = request.getScheme() + "://" + request.getServerName();
        ContractDownloadInitDTO contractDownloadInitDTO = downloadInit(contractId, SERVER_PATH);
        String outputFilePath = getFilePath(contractDownloadInitDTO, user);
        FileDownload.download(outputFilePath, response);
    }

    private ContractDownloadInitDTO downloadInit(String contractId,
                                                 String serverPath) throws ApplicationException {
        ContractDownloadInitDTO contractDownloadInitDTO = new ContractDownloadInitDTO();
        boolean contractFlag = false;
        if (StringUtils.isNotEmpty(contractId)) {
            // 当前合同
            ConContract conContract = conContractMapper.selectByPrimaryKey(contractId);
            if (null != conContract) {
                contractDownloadInitDTO = downloadInitConvertor.toDTO(conContract);
                // 服务地址
                contractDownloadInitDTO.setServerPath(serverPath);
            } else {
                contractFlag = true;
            }
        } else {
            contractFlag = true;
        }
        if (contractFlag) {
            log.error("contract does not exist");
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0106"));
        }
        return contractDownloadInitDTO;
    }

    /**
     * 取得支付周期
     *
     * @param type
     * @return
     */
    @Override
    public int getCycleFromType(int type) {
        if (SettlementCycleEnum.QUARTER_ENUM.getValue() == type) {
            return 3;
        } else if (SettlementCycleEnum.HALF_YEAR_ENUM.getValue() == type) {
            return 6;
        } else if (SettlementCycleEnum.YEAR_ENUM.getValue() == type) {
            return 12;
        } else {
            return 1;
        }
    }

    /**
     * 取得PDF路径
     *
     * @param contractDownloadInitDTO
     * @param user
     * @return
     */
    private String getFilePath(ContractDownloadInitDTO contractDownloadInitDTO,
                               UsrUser user) throws Exception {
        // 生成pdf路径
        String outputFilePath;
        // 模板路径
        String templatePath;
        // 图片映射真实地址
        String destDir = PropertyUtils.getSysVal("pdf.file.real.directory");
        // 图片映射临时地址
        String tempDir = PropertyUtils.getSysVal("pdf.file.temp.directory");
        // 附件列表
        List<SysInfoAtt> sysInfoAttList = contractDownloadInitDTO.getSysInfoAttList();
        // 当前合同
        ConContract conContract = contractDownloadInitDTO.getConContract();
        if (null == sysInfoAttList || sysInfoAttList.size() == 0) {
            // 租金分期
//            if (BY_STAGES.equals(conContract.getStages())) {
//                templatePath = "templates/pdfTemplates/threeContractTemStages1.0.0.html";
//            } else {
            templatePath = "pdfTemplates/threeContractTem1.0.0.html";
//            }
            PDFConContractDTO pdfConContractDTO = pdfContractConvertor.toDTO(contractDownloadInitDTO);
            outputFilePath = generatePdf(tempDir, pdfConContractDTO, conContract.getId(), templatePath);
            if (StringUtils.isEmpty(outputFilePath)) {
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0106"));
            }
            String inPdfFile = tempDir + "/" + outputFilePath;
            String outPdfFile = destDir + "/" + outputFilePath;
            File file = new File(destDir + DateUtils.getDate(DateUtils.DATE_Y_M_D_PATERN));
            if (!file.exists()) {
                file.mkdirs();
            }
            URL markImagePath = ResourceLoader.getResource("templates/pdfTemplates/images/contract-logo.png");
            log.info(markImagePath.toString());
            addPdfMark(inPdfFile, outPdfFile, markImagePath);
            // pdf插入到数据库
            SysInfoAtt sysInfoAtt = new SysInfoAtt();
            sysInfoAtt.setFileRealName(CONTRACT);
            sysInfoAtt.setFileType(InfoAttFileTypeEnum.DW_CON_PDF_ENUM.getValue());
            sysInfoAtt.setFileName(conContract.getId() + ".pdf");
            sysInfoAtt.setFilePath(outputFilePath);
            sysInfoAtt.setLid(conContract.getId());
            sysInfoAttServiceImpl.save(sysInfoAtt, user);
        } else {
            outputFilePath = sysInfoAttList.get(0).getFilePath();
            if (!new File(destDir + outputFilePath).exists()) {
                String lid = contractDownloadInitDTO.getConContract().getId();
                sysInfoAttServiceImpl.deleteOldSingleRecord(lid, InfoAttFileTypeEnum.DW_CON_PDF_ENUM.getValue(), user);
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0130"));
            }
        }
        return destDir + "/" + outputFilePath;
    }

    private String generatePdf(String destDir,
                               PDFConContractDTO pdfConContractDTO,
                               String id,
                               String templatePath) {
        File destFile = new File(destDir);
        String outputFilePath = DateUtils.getDate(DateUtils.DATE_Y_M_D_PATERN) + "/" + id + ".pdf";
        String fullOutputFilePath = destFile.getAbsolutePath() + "/" + outputFilePath;
        File file = new File(fullOutputFilePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
        // 生成pdf
        try {
            pdfGenerator.generate(templatePath, pdfConContractDTO, fullOutputFilePath);
        } catch (DocumentGeneratingException e) {
            this.log.info("PdfGeneratorUtil generatePdf DocumentGeneratingException", e);
            outputFilePath = null;
        }
        // 真实下载地址
        return outputFilePath;
    }

    /**
     * 给pdf文件添加水印
     *
     * @param inPdfFile     要加水印的原pdf文件路径
     * @param outPdfFile    加了水印后要输出的路径
     * @param markImagePath 水印图片路径
     * @throws Exception
     */
    public void addPdfMark(String inPdfFile,
                           String outPdfFile,
                           URL markImagePath) throws Exception {
        PdfReader reader = new PdfReader(inPdfFile);
        logger.info(reader);
        FileOutputStream outputStream = new FileOutputStream(outPdfFile);
        logger.info(outputStream);
        PdfStamper stamp = new PdfStamper(reader, outputStream);
        logger.info(stamp);
        Image image = Image.getInstance(markImagePath);// 插入水印
        logger.info(image);
        image.setAlignment(Image.UNDERLYING);
        int pageSize = reader.getNumberOfPages();
        float pageWidth;
        float pageHeight;
        float imageWidth = image.getWidth();
        float imageHeight = image.getHeight();
        int maxWidthNo;
        int maxHeightNo;
        for (int i = 1; i <= pageSize; i++) {
            PdfContentByte under = stamp.getUnderContent(i);
            Rectangle rectangle = reader.getPageSize(i);
            pageWidth = rectangle.getWidth();
            pageHeight = rectangle.getHeight();
            maxWidthNo = (int) (pageWidth / imageWidth);
            maxHeightNo = (int) (pageHeight / imageHeight);
            for (int j = 0; j <= maxWidthNo; j++) {
                for (int k = 0; k <= maxHeightNo; k++) {
                    image.setAbsolutePosition(j * imageWidth, k * imageHeight);
                    under.addImage(image);
                }
            }
        }
        stamp.close();// 关闭
        outputStream.close();
        reader.close();
    }
}
