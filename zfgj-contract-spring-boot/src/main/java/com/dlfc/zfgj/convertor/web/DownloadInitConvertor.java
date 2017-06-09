package com.dlfc.zfgj.convertor.web;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.ContractDownloadInitDTO;
import com.dlfc.zfgj.dto.web.SettlementCycleDTO;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.enums.InfoAttFileTypeEnum;
import com.dlfc.zfgj.service.web.DownloadService;
import com.dlfc.zfgj.service.web.HouCoOwnerTempService;
import com.dlfc.zfgj.service.web.SysInfoAttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by K on 2017/3/25.
 */

@Component
public class DownloadInitConvertor extends AbstractConvertor<ConContract, ContractDownloadInitDTO> {

    @Autowired
    private HouCoOwnerTempService houCoOwnerTempServiceImpl;
    @Autowired
    private SysInfoAttService sysInfoAttServiceImpl;
    @Autowired
    private DownloadService downloadServiceImpl;

    @Override
    public ConContract toModel(ContractDownloadInitDTO contractDownloadInitDTO) {
        return null;
    }

    @Override
    public ContractDownloadInitDTO toDTO(ConContract model, boolean forListView) {
        ContractDownloadInitDTO contractDownloadInitDTO = new ContractDownloadInitDTO();
        contractDownloadInitDTO.setConContract(model);
        // 附件列表
        contractDownloadInitDTO.setSysInfoAttList(sysInfoAttServiceImpl
                .findList(model.getId(), InfoAttFileTypeEnum.DW_CON_PDF_ENUM.getValue()));
        // 共有人列表
        if (StringUtils.isNotEmpty(model.getHid())) {
            contractDownloadInitDTO.setHouCoOwnerList(houCoOwnerTempServiceImpl.getHouCoOwnersByHid(model.getHid()));
        }
        // 支付列表
        contractDownloadInitDTO.setSettlementCycleList(getSettlemetCycle(model));
        return contractDownloadInitDTO;
    }

    /**
     * 取得支付方式
     *
     * @param conContract
     * @return
     */
    private List<SettlementCycleDTO> getSettlemetCycle(ConContract conContract) {
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
        int cycle = downloadServiceImpl.getCycleFromType(type);
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
        List<SettlementCycleDTO> settlementCycleList = new ArrayList();
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
            SettlementCycleDTO settlementCycleDTO = new SettlementCycleDTO();
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
}
