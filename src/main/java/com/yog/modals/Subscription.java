package com.yog.modals;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subscription")
@Entity
public class Subscription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long    id;
	private String  ani;
	private String  servicename;
	private String  status;
	private String  recordstatus;
	private LocalDateTime subdatetime;
	private LocalDateTime nextbilleddate;
	private LocalDateTime lastbilleddatetime;
	private LocalDateTime unsubdatetime;
	private String  default_amount;
	private String  packtype;
	private String  mod_act;
	private String  mod_deact;
	private String  lang;
	
	public  Subscription(String status)
	{
		this.status=status;
	}

}
