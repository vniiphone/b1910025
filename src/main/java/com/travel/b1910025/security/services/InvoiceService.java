package com.travel.b1910025.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import com.travel.b1910025.models.Invoice;
import com.travel.b1910025.payload.request.InvoiceRequest;
import com.travel.b1910025.payload.request.PaymentRequest;

@Component
public interface InvoiceService {
	Invoice getInvoice(long invoiceId);

	List<Invoice> getAllInvoices();

	List<Invoice> getAllUserInvoices(long userId);
	
	List<Invoice> getAllInvoicesPaySuccessByUser(long userId);

	Invoice creatInvoice(InvoiceRequest invoiceRequest);

	void deleteInvoiceById(Long invoiceId);

	Optional<Invoice> updateToursInInvoice(Invoice invoice, long cartItemsId);

	Optional<Invoice> setPaymentSuccess(Invoice invoice, String paymentMethod, Long userId);
}
