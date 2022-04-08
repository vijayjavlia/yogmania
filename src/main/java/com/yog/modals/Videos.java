package com.yog.modals;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
@Table(name = "tbl_videos")
public class Videos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name")
	private String videoName;
	@Column(name = "videoid")
	private String videoId;
	@Column(name = "vurl")
	private String videoUrl;
	@Column(name = "imgurl")
	private String imageUrl;
	@Column(name = "category")
	private String videoCategory;

	private String status;
	@Column(name = "sub_cat_id")
	private String videoSubCategory;
	private String description;



}
