package dao;

import java.util.List;

import entity.ChiTietHoaDonBanHang;

public interface ChiTietBanHangDAO {
	public boolean themChiTietHoaDon(ChiTietHoaDonBanHang chiTietHoaDon);
	public boolean capNhatChiTietHoaDon(ChiTietHoaDonBanHang chiTietHoaDon);
	public boolean xoaChiTietHoaDon(ChiTietHoaDonBanHang chiTietHoaDon);
	public double tinhThanhTien(ChiTietHoaDonBanHang chiTietHoaDon);
	public List<ChiTietHoaDonBanHang> layDanhSachCTHoaDonBanHangTheoMaHDBH(String ma);
	public List<ChiTietHoaDonBanHang> layDanhSachCTHoaDonBanHangTheoMaSP(String ma);
}
