package com.binar.batch7.service;

import net.sf.jasperreports.engine.JasperPrint;

public interface InvoiceService {
    JasperPrint generateInvoice();

    JasperPrint generateReportingMerchant();
}
