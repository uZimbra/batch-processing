package com.rodrigo.batch_processing;

import com.rodrigo.batch_processing.domain.BalanceInfo;
import com.rodrigo.batch_processing.domain.FileHeader;
import com.rodrigo.batch_processing.domain.StatementHeader;

import java.util.List;

public class Invoice {

    private FileHeader fileHeader;
    private List<StatementHeader> statementHeader;
    private List<BalanceInfo> balanceInfo;

    public Invoice() {}

    public Invoice(FileHeader fileHeader, List<StatementHeader> statementHeader, List<BalanceInfo> balanceInfo) {
        this.fileHeader = fileHeader;
        this.statementHeader = statementHeader;
        this.balanceInfo = balanceInfo;
    }

    public FileHeader getFileHeader() {
        return fileHeader;
    }

    public void setFileHeader(FileHeader fileHeader) {
        this.fileHeader = fileHeader;
    }

    public List<StatementHeader> getStatementHeader() {
        return statementHeader;
    }

    public void setStatementHeader(List<StatementHeader> statementHeader) {
        this.statementHeader = statementHeader;
    }

    public List<BalanceInfo> getBalanceInfo() {
        return balanceInfo;
    }

    public void setBalanceInfo(List<BalanceInfo> balanceInfo) {
        this.balanceInfo = balanceInfo;
    }
}
