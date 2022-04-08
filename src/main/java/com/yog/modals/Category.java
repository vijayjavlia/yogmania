package com.yog.modals;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_cat")
public class Category {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="category_name")
	private String categoryid;
	@Column(name="name")
	private String categoryName;
	@Column(name="status")
	private String status;
	private String age;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="category")
	private List<Videos> videos=new ArrayList<Videos>();
}
