package dao;

import java.util.List;

import entity.KhachHang;

public interface KhachHangDAO {
	public boolean themKhachHang(KhachHang khachHang);
	public boolean capNhatKhachHang(KhachHang khachHang);
	public boolean xoaKhachHang(String ma);
	public List<KhachHang> layTatCaKhachHang();
	public KhachHang layKhachHangTheoMa(String ma);
	public List<KhachHang> layDanhSachKhachHangTheoTen(String ten);
	public KhachHang layKhachHangTheoDienthoai(String sdt);
	public List<KhachHang> layDanhSachKhachhangTheoNamSinh(int nam);
	public KhachHang layKhachhangTheoCMND(String cmnd);
	public List<KhachHang> layDanhSachKhachHangTheoTen_SDT_CMND(String key);

}
