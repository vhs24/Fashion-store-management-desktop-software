package dao;

import java.util.List;

import entity.NhanVienBanHang;

public interface NhanVienDAO {
	public boolean themNhanVien(NhanVienBanHang nhanVien);
	public boolean capNhatNhanVien(NhanVienBanHang nhanVien);
	public boolean xoaNhanVien (String ma);
	public List<NhanVienBanHang> layTatCaNhanVien();
	public NhanVienBanHang layNhanVienTheoMa(String ma);
	public List<NhanVienBanHang> layDanhSachNhanVienTheoTen(String ten);
	public NhanVienBanHang layNhanVienTheoSoDienthoai(String sdt);
	public List<NhanVienBanHang> layDanhSachNhanVienTheoNamSinh(int nam);
	public List<NhanVienBanHang> layDanhSachNhanVienTheoNgayVaoLam(String date);
	public NhanVienBanHang capnhatTinhTrangNhanVien(String ma);
	public List<NhanVienBanHang> layDanhSachNhanVienTheoGioiTinh(boolean gt);
	public List<NhanVienBanHang> layDanhSachNhanVienTheoTinhTrang(boolean tinhTrang);
	public NhanVienBanHang layNhanVienTheoSoCMND(String cmnd);
	public List<NhanVienBanHang> layDanhSachNhanVienTheoTen_SDT_CMND(String key);
}
