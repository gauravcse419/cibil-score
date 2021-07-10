package com.hcl.hackathon.entity;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cibil_score database table.
 *
 */
@Entity
@Table(name="cibil_score")
@NamedQuery(name="CibilScoreEntity.findAll", query="SELECT c FROM CibilScoreEntity c")
@Data
public class CibilScoreEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="cibil_id")
    private Integer cibilId;

    @Column(name="cibil_score")
    private double cibilScore;

    private String name;

    @Column(name="pancard_no")
    private String panCardNo;


}