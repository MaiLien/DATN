package datn.entity;

import datn.constant.TypeOfUser;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="NguoiDung.findAll")
@DiscriminatorColumn(name="loaiNguoiDung", discriminatorType=DiscriminatorType.INTEGER)
public class NguoiDung implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String maNguoiDung;

	private String email;

	private String ghiChu;

	private TypeOfUser loaiNguoiDung;

	private String matKhau;

	@Temporal(TemporalType.DATE)
	private Date ngaySinh;

	private String sdt;

	private String ten;

	private int trangThai;

	//bi-directional many-to-one association to GiangVien
	@OneToMany(mappedBy="nguoiDung", fetch=FetchType.EAGER)
	private List<GiangVien> giangViens;

	public NguoiDung() {
	}

	public String getMaNguoiDung() {
		return this.maNguoiDung;
	}

	public void setMaNguoiDung(String maNguoiDung) {
		this.maNguoiDung = maNguoiDung;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGhiChu() {
		return this.ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public TypeOfUser getLoaiNguoiDung() {
		return this.loaiNguoiDung;
	}

	public void setLoaiNguoiDung(TypeOfUser loaiNguoiDung) {
		this.loaiNguoiDung = loaiNguoiDung;
	}

	public String getMatKhau() {
		return this.matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public Date getNgaySinh() {
		return this.ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getSdt() {
		return this.sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getTen() {
		return this.ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public int getTrangThai() {
		return this.trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public List<GiangVien> getGiangViens() {
		return this.giangViens;
	}

	public void setGiangViens(List<GiangVien> giangViens) {
		this.giangViens = giangViens;
	}

	public GiangVien addGiangVien(GiangVien giangVien) {
		getGiangViens().add(giangVien);
		giangVien.setNguoiDung(this);

		return giangVien;
	}

	public GiangVien removeGiangVien(GiangVien giangVien) {
		getGiangViens().remove(giangVien);
		giangVien.setNguoiDung(null);

		return giangVien;
	}

}