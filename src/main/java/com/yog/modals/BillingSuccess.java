package com.yog.modals;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "billingsuccess")
@Entity
public class BillingSuccess {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ani;
	private String servicename;
	private String packtype;
	private String defaultamount;
	private LocalDateTime subdatetime;
	private LocalDateTime billeddate;
	private LocalDateTime processdatetime;
	private LocalDateTime nextbilleddattime;
	private String typeevent;
	private String totalamount;
	private String deductedamount;
	private String recordstatus;
	private String errdesc;
	private String trxid;
	private String mode;
	
	
	
	

	
}
