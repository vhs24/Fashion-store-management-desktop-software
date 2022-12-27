package dao;

import java.util.List;

import entity.ChiTietHoaDonBanHang;
import entity.ChiTietHoaDonNhapKho;
import entity.HoaDonNhapKho;

public interface ChiTietNhapKhoDAO {
	public boolean themChiTietNhapKho(ChiTietHoaDonNhapKho chiTietNhapKho);
	public boolean capNhatChiTietNhapKho(ChiTietHoaDonNhapKho chiTietNhapKho);
	public boolean xoaChiTietNhapKho(ChiTietHoaDonNhapKho chiTietNhapKho);
	public List<ChiTietHoaDonNhapKho> layDanhSachCTHoaDonNhapKhoTheoMaHDBH(String ma);
	public List<ChiTietHoaDonNhapKho> layDanhSachCTHoaDonNhapKhoTheoMaSP(String ma);
}
