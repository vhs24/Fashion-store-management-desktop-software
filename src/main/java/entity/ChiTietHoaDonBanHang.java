package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="ChiTietHoaDonBanHang")
@IdClass(CompositeKeyCTBH.class)
public class ChiTietHoaDonBanHang {
	@Column(name= "soLuong")
	private int soLuong;
	@Column(name="thanhTien")
	private double thanhTien;
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maSanPham")
	private SanPham sanPham;
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maHoaDonBanHang")
	private HoaDonBanHang hoaDonBanHang;

	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	public HoaDonBanHang getHoaDonBanHang() {
		return hoaDonBanHang;
	}
	public void setHoaDonBanHang(HoaDonBanHang hoaDonBanHang) {
		this.hoaDonBanHang = hoaDonBanHang;
	}
	
	public double getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}
	public ChiTietHoaDonBanHang(int soLuong) {
		super();
		this.soLuong = soLuong;
	}
	public ChiTietHoaDonBanHang() {
		super();
	}
	
	public ChiTietHoaDonBanHang(int soLuong, SanPham sanPham, HoaDonBanHang hoaDonBanHang) {
		super();
		this.soLuong = soLuong;
		this.sanPham = sanPham;
		this.hoaDonBanHang = hoaDonBanHang;
	}
	
	@Override
	public String toString() {
		return "ChiTietHoaDonBanHang [soLuong=" + soLuong + ", sanPham=" + sanPham + ", hoaDonBanHang=" + hoaDonBanHang
				+ "]";
	}
	
	
	
}
