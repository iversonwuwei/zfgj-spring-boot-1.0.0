package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.*;
import com.dlfc.zfgj.common.pdf.pdf.PdfDocumentGenerator;
import com.dlfc.zfgj.common.pdf.utils.ResourceLoader;
import com.dlfc.zfgj.dto.moblie.ContractDownloadInitMDTO;
import com.dlfc.zfgj.dto.moblie.PDFConContractMDTO;
import com.dlfc.zfgj.dto.moblie.SettlementCycleMDTO;
import com.dlfc.zfgj.entity.*;
import com.dlfc.zfgj.enums.InfoAttFileTypeEnum;
import com.dlfc.zfgj.enums.SettlementCycleEnum;
import com.dlfc.zfgj.enums.StructureEnum;
import com.dlfc.zfgj.exception.DocumentGeneratingException;
import com.dlfc.zfgj.mapper.ConContractMapper;
import com.dlfc.zfgj.service.mobile.DownloadMService;
import com.dlfc.zfgj.service.mobile.HouCoOwnerMService;
import com.dlfc.zfgj.service.mobile.SysCodeMService;
import com.dlfc.zfgj.service.mobile.SysInfoAttMService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by K on 2017/3/13.
 */

@Service
@Transactional
@Slf4j
public class DownloadMServiceImpl implements DownloadMService {
    // 租金分期
    private static final String BY_STAGES = "01";
    // 房主承担其它费用的编号
    public static final int OWNER_BEAR_OTHER_FLAG = 13;
    // 分隔符：","
    public static final String SEPARATOR = ",";
    // 房主承担
    public static final String LESSOR_BEAR = "lessor_bear";
    // 签约当日甲方提供的证件原件
    private static final String LESSOR_ORIGI_LICENSE = "lessor_origi_license";
    // 签约当日乙方提供的证件原件
    private static final String LESSEE_ORIGI_LICENSE = "lessee_origi_license";
    // 静态变量合同
    private static final String CONTRACT = "合同";

    @Autowired
    private ConContractMapper conContractMapper;
    @Autowired
    private SysInfoAttMService sysInfoAttMServiceImpl;
    @Autowired
    private HouCoOwnerMService houCoOwnerMServiceImpl;
    @Autowired
    private SysCodeMService sysCodeMServiceImpl;

    /**
     * PDF下载
     *
     * @param contractId
     * @param request
     * @param response
     * @throws ApplicationException
     */
    //TODO n
    @Override
    public void download(String contractId,
                         HttpServletRequest request,
                         HttpServletResponse response,
                         UsrUser user) throws ApplicationException {
        String serverPath = request.getScheme() + "://" + request.getServerName();
        ContractDownloadInitMDTO contractDownloadInitDTO = downloadInit(contractId, serverPath);
        String outputFilePath = getFilePath(contractDownloadInitDTO,user);
        FileDownload.download(outputFilePath, response);
    }

    private ContractDownloadInitMDTO downloadInit(String contractId,
                                                 String serverPath) throws ApplicationException {
        ContractDownloadInitMDTO contractDownloadInitDTO = new ContractDownloadInitMDTO();
        boolean contractFlag = false;
        if (StringUtils.isNotEmpty(contractId)) {
            // 当前合同
            ConContract conContract = conContractMapper.selectByPrimaryKey(contractId);
            if (null != conContract) {
                contractDownloadInitDTO.setConContract(conContract);
                // 服务地址
                contractDownloadInitDTO.setServerPath(serverPath);
                // 附件列表
                contractDownloadInitDTO.setSysInfoAttList(sysInfoAttMServiceImpl
                        .findList(contractId, InfoAttFileTypeEnum.DW_CON_PDF_ENUM.getValue()));
                // 共有人列表
                contractDownloadInitDTO.setHouCoOwnerList(houCoOwnerMServiceImpl.getHouCoOwnersByHid(conContract.getHid()));
                // 支付列表
                contractDownloadInitDTO.setSettlementCycleList(getSettlemetCycle(conContract));
            } else {
                contractFlag = true;
            }
        } else {
            contractFlag = true;
        }
        if (contractFlag) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0106"));
        }
        return contractDownloadInitDTO;
    }

    /**
     * 取得支付方式
     *
     * @param conContract
     * @return
     */
    private List<SettlementCycleMDTO> getSettlemetCycle(ConContract conContract) {
        Date startTime = conContract.getStartTime();
        Date endTime = conContract.getEndTime();
        int type = conContract.getSettlementCycle();
        double money = conContract.getMonthlyRent().doubleValue();
        int year = conContract.getLeaseTermYear();
        int month = conContract.getLeaseTermMonth();

        SimpleDateFormat formatYYYY = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMM = new SimpleDateFormat("MM");
        SimpleDateFormat formatDD = new SimpleDateFormat("dd");
        // 总月数
        int totalMonth = year * 12 + month;
        int cycle = getCycleFromType(type);
        Calendar clStartTime = Calendar.getInstance();
        clStartTime.setTime(startTime);
        int payStartDay = clStartTime.get(Calendar.DATE);
        // 支付时间
        Date afterPayDate;
        // 支付起始有效日期
        Date fromDate;
        // 支付结束有效日期
        Date toDate;
        // 支付金额
        double rent = 0;
        List<SettlementCycleMDTO> settlementCycleList = new ArrayList();
        for (int i = 0; i < totalMonth; i = i + cycle) {
            int surplus = totalMonth - i;
            if (i == 0) {
                afterPayDate = startTime;
                fromDate = startTime;
                Calendar cl = Calendar.getInstance();
                cl.setTime(startTime);
                cl.add(Calendar.MONTH, cycle);
                int days = cl.getActualMaximum(Calendar.DATE);
                if (payStartDay <= days) {
                    cl.set(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH), payStartDay - 1);
                } else {
                    cl.set(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH), days - 1);
                }
                toDate = cl.getTime();
                rent = money * cycle;
            } else {
                if (cycle == 1) {
                    Calendar cl = Calendar.getInstance();
                    cl.setTime(startTime);
                    cl.add(Calendar.MONTH, i);
                    int firstDays = cl.getActualMaximum(Calendar.DATE);
                    if (payStartDay > firstDays) {
                        cl.set(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH), firstDays);
                    }
                    afterPayDate = cl.getTime();
                    fromDate = afterPayDate;
                    Calendar cls = Calendar.getInstance();
                    cls.setTime(startTime);
                    cls.add(Calendar.MONTH, cycle + i);
                    int secondDays = cls.getActualMaximum(Calendar.DATE);
                    if (payStartDay <= secondDays) {
                        cls.set(cls.get(Calendar.YEAR), cls.get(Calendar.MONTH), payStartDay - 1);
                    } else {
                        cls.set(cl.get(Calendar.YEAR), cls.get(Calendar.MONTH), secondDays - 1);
                    }
                    toDate = cls.getTime();
                    rent = money * cycle;
                } else {
                    Calendar cl = Calendar.getInstance();
                    cl.setTime(startTime);
                    cl.add(Calendar.MONTH, i - 1);
                    int firstDays = cl.getActualMaximum(Calendar.DATE);
                    if (payStartDay > firstDays) {
                        cl.set(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH), firstDays);
                    }
                    afterPayDate = cl.getTime();
                    Calendar cls = Calendar.getInstance();
                    cls.setTime(startTime);
                    cls.add(Calendar.MONTH, i);
                    int secondDays = cls.getActualMaximum(Calendar.DATE);
                    if (payStartDay > secondDays) {
                        cls.set(cls.get(Calendar.YEAR), cls.get(Calendar.MONTH), secondDays);
                    }
                    fromDate = cls.getTime();
                    if (surplus >= cycle) {
                        Calendar clss = Calendar.getInstance();
                        clss.setTime(startTime);
                        clss.add(Calendar.MONTH, i + cycle);
                        int thirdDays = clss.getActualMaximum(Calendar.DATE);
                        if (payStartDay <= thirdDays) {
                            clss.set(clss.get(Calendar.YEAR), clss.get(Calendar.MONTH), payStartDay - 1);
                        } else {
                            clss.set(clss.get(Calendar.YEAR), clss.get(Calendar.MONTH), thirdDays - 1);
                        }
                        toDate = clss.getTime();
                        rent = money * cycle;
                    } else {
                        toDate = endTime;
                        rent = money * surplus;
                    }
                }
            }
            SettlementCycleMDTO settlementCycleDTO = new SettlementCycleMDTO();
            settlementCycleDTO.setAfterPayDateYYYY(formatYYYY.format(afterPayDate));
            settlementCycleDTO.setAfterPayDateMM(formatMM.format(afterPayDate));
            settlementCycleDTO.setAfterPayDateDD(formatDD.format(afterPayDate));
            settlementCycleDTO.setFromDateYYYY(formatYYYY.format(fromDate));
            settlementCycleDTO.setFromDateMM(formatMM.format(fromDate));
            settlementCycleDTO.setFromDateDD(formatDD.format(fromDate));
            settlementCycleDTO.setToDateYYYY(formatYYYY.format(toDate));
            settlementCycleDTO.setToDateMM(formatMM.format(toDate));
            settlementCycleDTO.setToDateDD(formatDD.format(toDate));
            settlementCycleDTO.setMoney(rent);
            settlementCycleList.add(settlementCycleDTO);
        }
        return settlementCycleList;
    }

    /**
     * 取得支付周期
     *
     * @param type
     * @return
     */
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
     * @return
     */
    //TODO n
    private String getFilePath(ContractDownloadInitMDTO contractDownloadInitDTO, UsrUser user) throws ApplicationException {
        // 生成pdf路径
        String outputFilePath = StringUtils.EMPTY;
        // 模板路径
        String templatePath = StringUtils.EMPTY;
        // 图片映射地址
        String destDir = PropertyUtils.getSysVal("pdf.file.real.directory");
        // 附件列表
        List<SysInfoAtt> sysInfoAttList = contractDownloadInitDTO.getSysInfoAttList();
        // 当前合同
        ConContract conContract = contractDownloadInitDTO.getConContract();
        if (null == sysInfoAttList || sysInfoAttList.size() == 0) {
            // 租金分期
            if (BY_STAGES.equals(conContract.getStages())) {
                templatePath = "templates/pdfTemplates/threeContractTemStages1.0.0.html";
            } else {
                templatePath = "templates/pdfTemplates/threeContractTem1.0.0.html";
            }
            PDFConContractMDTO pdfConContractDTO = getPDFConContractMDTO(contractDownloadInitDTO);
            outputFilePath = generatePdf(destDir, pdfConContractDTO, conContract.getId(), templatePath);
            if (StringUtils.isEmpty(outputFilePath)) {
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0106"));
            }
            // pdf插入到数据库
            SysInfoAtt sysInfoAtt = new SysInfoAtt();
            sysInfoAtt.setFileRealName(CONTRACT);
            sysInfoAtt.setFileType(InfoAttFileTypeEnum.DW_CON_PDF_ENUM.getValue());
            sysInfoAtt.setFileName(conContract.getNo() + ".pdf");
            sysInfoAtt.setFilePath(outputFilePath);
            sysInfoAtt.setLid(conContract.getId());
            sysInfoAttMServiceImpl.save(sysInfoAtt,user);//TODO n
        } else {
            outputFilePath = sysInfoAttList.get(0).getFilePath();
        }
        return outputFilePath;//TODO 没完事儿哪
    }

    private String generatePdf(String destDir,
                               PDFConContractMDTO pdfConContractDTO,
                               String id,
                               String templatePath) {
        File destFile = new File(destDir);
        String outputFilePath = DateUtils
                .dateToStr(DateUtils.getSynchTime(), DateUtils.DATE_Y_M_D_PATERN) + "/" + id + ".pdf";
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
        return destDir + "/" + outputFilePath;
    }

    /**
     * 取得三方合同数据
     *
     * @param contractDownloadInitDTO
     * @return
     */
    private PDFConContractMDTO getPDFConContractMDTO(ContractDownloadInitMDTO contractDownloadInitDTO) {
        ConContract conContract = contractDownloadInitDTO.getConContract();
        String serverPath = contractDownloadInitDTO.getServerPath();
        List<HouCoOwner> houCoOwnerList = contractDownloadInitDTO.getHouCoOwnerList();
        List<SettlementCycleMDTO> settlementCycleList = contractDownloadInitDTO.getSettlementCycleList();
        PDFConContractMDTO pdfConContractDTO = new PDFConContractMDTO();
        //设置pdf合同属性值
        pdfConContractDTO.setNo(conContract.getNo());
        pdfConContractDTO.setLessorName(conContract.getLessorName());
        pdfConContractDTO.setLessorIdType(conContract.getLessorIdTypeName());
        pdfConContractDTO.setLessorIdNo(conContract.getLessorIdNo());
        if ((StringUtils.EMPTY).equals(conContract.getLessorAddress())) {
            pdfConContractDTO.setLessorAddress(null);
        } else {
            pdfConContractDTO.setLessorAddress(conContract.getLessorAddress());
        }
        pdfConContractDTO.setLessorMobile(conContract.getLessorMobile());

        if ((StringUtils.EMPTY).equals(conContract.getLessorEmail())) {
            pdfConContractDTO.setLessorEmail(null);
        } else {
            pdfConContractDTO.setLessorEmail(conContract.getLessorEmail());
        }

        if ((StringUtils.EMPTY).equals(conContract.getLessorBackupName())) {
            pdfConContractDTO.setLessorBakName(null);
        } else {
            pdfConContractDTO.setLessorBakName(conContract.getLessorBackupName());
        }

        if ((StringUtils.EMPTY).equals(conContract.getLessorBackupTel())) {
            pdfConContractDTO.setLessorBakMobile(null);
        } else {
            pdfConContractDTO.setLessorBakMobile(conContract.getLessorBackupTel());
        }

        pdfConContractDTO.setLesseeName(conContract.getLesseeName());
        pdfConContractDTO.setLesseeIdType(conContract.getLesseeIdTypeName());
        pdfConContractDTO.setLesseeIdNo(conContract.getLesseeIdNo());
        if ((StringUtils.EMPTY).equals(conContract.getLesseeAddress())) {
            pdfConContractDTO.setLesseeAddress(null);
        } else {
            pdfConContractDTO.setLesseeAddress(conContract.getLesseeAddress());
        }
        pdfConContractDTO.setLesseeMobile(conContract.getLesseeMobile());
        if ((StringUtils.EMPTY).equals(conContract.getLesseeEmail())) {
            pdfConContractDTO.setLesseeEmail(null);
        } else {
            pdfConContractDTO.setLesseeEmail(conContract.getLesseeEmail());
        }
        if ((StringUtils.EMPTY).equals(conContract.getLesseeBackupName())) {
            pdfConContractDTO.setLesseeBakName(null);
        } else {
            pdfConContractDTO.setLesseeBakName(conContract.getLesseeBackupName());
        }
        if ((StringUtils.EMPTY).equals(conContract.getLesseeBackupTel())) {
            pdfConContractDTO.setLesseeBakMobile(null);
        } else {
            pdfConContractDTO.setLesseeBakMobile(conContract.getLesseeBackupTel());
        }

        pdfConContractDTO.setCompanyName(conContract.getAgtEmpInfo().getAgtCompInfo().getFullName());
        pdfConContractDTO.setCompanyAddress(conContract.getAgtEmpInfo().getAgtCompInfo().getAddress());
        pdfConContractDTO.setCompanyTel(conContract.getAgtEmpInfo().getAgtCompInfo().getTel());

        pdfConContractDTO.setManageName(conContract.getAgtEmpInfo().getSysPerson().getName());
        pdfConContractDTO.setManageIdNo(conContract.getAgtEmpInfo().getSysPerson().getIdNo());
        pdfConContractDTO.setManageMobile(conContract.getAgtEmpInfo().getPhone());

        pdfConContractDTO.setHouseAddr(conContract.getHouseAddr());
        pdfConContractDTO.setHouseType(StructureEnum.getName(conContract.getHouseInfo().getStructure()));
        pdfConContractDTO.setProperType(conContract.getPropertyIdTypeName());
        pdfConContractDTO.setHousePropertyIdNo(conContract.getHouseInfo().getNo());
        pdfConContractDTO.setHouseBuildingArea(conContract.getBuildingArea());
        pdfConContractDTO.setLeaseArea(conContract.getLeaseArea());
        pdfConContractDTO.setLeasePurpose(conContract.getRentalPurposeName());

        pdfConContractDTO.setHouCoOwnerList(houCoOwnerList);
        pdfConContractDTO.setConHouseUserList(conContract.getHouseUsers());

        pdfConContractDTO.setLeaseTermYear(conContract.getLeaseTermYear());
        pdfConContractDTO.setLeaseTermMonth(conContract.getLeaseTermMonth());
        pdfConContractDTO.setYear(DateUtils.dateToStr(conContract.getStartTime(), "yyyy"));
        pdfConContractDTO.setMonth(DateUtils.dateToStr(conContract.getStartTime(), "MM"));
        pdfConContractDTO.setDay(DateUtils.dateToStr(conContract.getStartTime(), "dd"));
        pdfConContractDTO.setEndYear(DateUtils.dateToStr(conContract.getEndTime(), "yyyy"));
        pdfConContractDTO.setEndMonth(DateUtils.dateToStr(conContract.getEndTime(), "MM"));
        pdfConContractDTO.setEndDay(DateUtils.dateToStr(conContract.getEndTime(), "dd"));
        pdfConContractDTO.setMonthlyRent(conContract.getMonthlyRent());
        pdfConContractDTO.setMonthlyRentCN(
                NumberToCN.number2CNMontrayUnit(new BigDecimal(conContract.getMonthlyRent())));
        pdfConContractDTO.setSettlementCycle(conContract.getSettlementCycleName());
        pdfConContractDTO.setOwnerBear(conContract.getOwnerBear());

        if (StringUtils.isNotEmpty(conContract.getOwnerBearOther())) {
            if (StringUtils.isNotEmpty(conContract.getOwnerBear())) {
                pdfConContractDTO.setOwnerBear(pdfConContractDTO.getOwnerBear() + SEPARATOR + OWNER_BEAR_OTHER_FLAG);
            } else {
                pdfConContractDTO.setOwnerBear(String.valueOf(OWNER_BEAR_OTHER_FLAG));
            }
        }

        pdfConContractDTO.setOwnerBearOther(conContract.getOwnerBearOther());
        pdfConContractDTO.setLesseeBear(this.leaseBear(conContract.getOwnerBear()));
        pdfConContractDTO.setDepositAmt(conContract.getDepositAmt());
        pdfConContractDTO.setDepositAmtCN(NumberToCN.number2CNMontrayUnit(conContract.getDepositAmt()));
        pdfConContractDTO.setAgencyFee(conContract.getAgencyFee());
        pdfConContractDTO.setAgencyFeeCN(NumberToCN.number2CNMontrayUnit(conContract.getAgencyFee()));
        pdfConContractDTO.setLessorIds(
                this.getName(conContract.getLessorIds(), sysCodeMServiceImpl.getCodeByType(LESSOR_ORIGI_LICENSE)));
        pdfConContractDTO.setLessorIdsOther(conContract.getLessorIdsOther());
        pdfConContractDTO.setLesseeIds(
                this.getName(conContract.getLesseeIds(), sysCodeMServiceImpl.getCodeByType(LESSEE_ORIGI_LICENSE)));
        pdfConContractDTO.setLesseeIdsOther(conContract.getLesseeIdsOther());
        pdfConContractDTO.setSettlementCycleList(settlementCycleList);
        pdfConContractDTO.setImagePath(ResourceLoader.getPath("templates/pdfTemplates/images"));
        pdfConContractDTO.setAdditionalTerms(conContract.getAdditionalTerms());
        if (conContract.getAgtEmpInfo().getAgtCompInfo().getEchapterEnabled() != null
                && Integer.valueOf(conContract.getAgtEmpInfo().getAgtCompInfo().getEchapterEnabled()) == 1) {
            pdfConContractDTO.setImageUrl(serverPath + conContract.getAgtEmpInfo().getAgtCompInfo().getEchapterPath());
        }
        return pdfConContractDTO;
    }

    /**
     * 获取承租方承担的费用
     *
     * @param lessorBear
     * @return
     */
    private String leaseBear(String lessorBear) {
        String lesseeBear = StringUtils.EMPTY;
        if (StringUtils.isNotEmpty(lessorBear)) {
            String[] lessorBearArray = lessorBear.split(SEPARATOR);
            List<SysCode> bears = sysCodeMServiceImpl.getCodeByType(LESSOR_BEAR);
            if (bears != null) {
                for (SysCode code : bears) {
                    boolean flg = true;
                    if (lessorBear != null) {
                        for (String bear : lessorBearArray) {
                            if (code.getCode().equals(bear)) {
                                flg = false;
                                break;
                            }
                        }
                    }
                    if (flg) {
                        lesseeBear = lesseeBear + SEPARATOR + code.getCode();
                    }
                }
            }
            if (lesseeBear.startsWith(SEPARATOR)) {
                lesseeBear = lesseeBear.substring(1);
            }
        }
        return lesseeBear;
    }

    /**
     * 根据code值获取标签名称
     *
     * @param codeList
     * @param feature  code字符串
     * @return 标签名字
     */
    private String getName(String feature, List<SysCode> codeList) {
        // 标签名字
        String featureName = StringUtils.EMPTY;
        if (StringUtils.isNotEmpty(feature)) {
            // 获取code字符数组
            String[] featureArry = feature.split(SEPARATOR);
            for (String f : featureArry) {
                for (SysCode code : codeList) {
                    if (StringUtils.isNotEmpty(f) && f.equals(code.getCode())) {
                        featureName = featureName + StringUtils.SPACE + code.getName();
                    }
                }
            }
        }
        return featureName;
    }
}
