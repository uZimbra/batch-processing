package com.rodrigo.batch_processing.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    private StatementHeader statementHeader;
    private List<BalanceInfo> balanceInfo;

}
