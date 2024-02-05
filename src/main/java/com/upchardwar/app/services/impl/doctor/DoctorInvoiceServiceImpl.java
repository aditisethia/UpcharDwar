package com.upchardwar.app.services.impl.doctor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.upchardwar.app.entity.doctor.Appointment;
import com.upchardwar.app.entity.doctor.DoctorInvoice;
import com.upchardwar.app.entity.payload.AppointmentRequest;
import com.upchardwar.app.repository.DoctorInvoiceRepository;
import com.upchardwar.app.services.doctor.IDoctorInvoiceService;

@Service
public class DoctorInvoiceServiceImpl implements IDoctorInvoiceService {


	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DoctorInvoiceRepository doctorInvoiceRepository;
	
	

	 @Override
	    public DoctorInvoice createDoctorInvoice(DoctorInvoice doctorInvoice) {
	        
	        return doctorInvoiceRepository.save(doctorInvoice);
	    }



	@Override
	public Page<DoctorInvoice> getInvoiceByDoctorId(Long doctorId, Pageable pageable) {
		Page<DoctorInvoice> doctorinvoice = doctorInvoiceRepository.findByDoctorId(doctorId, pageable);
        return doctorinvoice.map(this::convertToRequestDto);
	}



    private DoctorInvoice convertToRequestDto(DoctorInvoice doctorInvoice) {
        return modelMapper.map(doctorInvoice, DoctorInvoice.class);
    }



}
