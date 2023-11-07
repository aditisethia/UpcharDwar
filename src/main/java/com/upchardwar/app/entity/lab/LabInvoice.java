package com.upchardwar.app.entity.lab;

import java.time.LocalDate;
import java.util.List;

import com.upchardwar.app.entity.patient.Patient;
import com.upchardwar.app.entity.status.InvoiceStatus;
import com.upchardwar.app.entity.status.LabStatus;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class LabInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private  Long labInvoiceId;
    
    private LocalDate invoiceGenerDate ;
    
    private float amount;
    
	@ElementCollection(fetch = FetchType.EAGER)
	private List<InvoiceStatus> invoiceStatus;
    
    private String paymentMethod;
     
    @ManyToOne
    private Lab lab;
    
    @ManyToOne
    private Patient patient;
}
