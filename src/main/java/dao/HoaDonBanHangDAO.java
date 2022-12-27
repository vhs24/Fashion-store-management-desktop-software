package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeMap;

import entity.HoaDonBanHang;
public interface HoaDonBanHangDAO {
	public boolean themHoaDonBanHang(HoaDonBanHang hoaDonBanHang);
	public boolean capNhatHoaDonBanHang(HoaDonBanHang hoaDonBanHang);
	public List<HoaDonBanHang> layTatCaHoaDonBanHang();
	public HoaDonBanHang layHoaDonTheoMa(String ma);
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoNgay(int year, int month, int day);
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoTenKhachHang(String tenKH);
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoMaKhachHang(String maKH);
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoMaNhanVien(String maNV);
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoTenNhanVien(String tenNV);
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoTenKhachHang_SDT_CMND(String key);
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoTenKhachHang_SDT_CMND_Ngay(String key, int year, int month, int day);
	public double tinhTongTien(String ma);
	public List<HoaDonBanHang> layDanhSachHoaDonTheoCumNgay(Date from,Date and);
	public TreeMap<String, Double> thongkeDoanhThu(Date from,Date and);
	public TreeMap<String, Double> thongkeSLDH(Date from, Date and) throws ClassNotFoundException, SQLException;
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoThang(int year, int month);
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoNam(int year);
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoThang_SP(int year, int month, String ma);
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoNam_SP(int year, String ma);
	public List<HoaDonBanHang> layDanhSachHoaDonTheoCumNgay_SP(Date from,Date and, String ma);
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoNgay_SP(int year, int month, int day, String ma);
}
