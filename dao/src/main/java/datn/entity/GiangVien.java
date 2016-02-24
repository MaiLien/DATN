package datn.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the GiangVien database table.
 * 
 */
@Entity
@NamedQuery(name="GiangVien.findAll", query="SELECT g FROM GiangVien g")
public class GiangVien implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String maGiangVien;

	private String chuyenNghanh;

	private String hocVi;

	//bi-directional many-to-one association to NguoiDung
	@ManyToOne
	@JoinColumn(name="maNguoiDung")
	private NguoiDung nguoiDung;

	public GiangVien() {
	}

	public String getMaGiangVien() {
		return this.maGiangVien;
	}

	public void setMaGiangVien(String maGiangVien) {
		this.maGiangVien = maGiangVien;
	}

	public String getChuyenNghanh() {
		return this.chuyenNghanh;
	}

	public void setChuyenNghanh(String chuyenNghanh) {
		this.chuyenNghanh = chuyenNghanh;
	}

	public String getHocVi() {
		return this.hocVi;
	}

	public void setHocVi(String hocVi) {
		this.hocVi = hocVi;
	}

	public NguoiDung getNguoiDung() {
		return this.nguoiDung;
	}

	public void setNguoiDung(NguoiDung nguoiDung) {
		this.nguoiDung = nguoiDung;
	}

}