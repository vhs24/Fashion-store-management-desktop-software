package dao;

import java.util.List;

import entity.HoaDonNhapKho;
public interface HoaDonNhapKhoDAO {
	public boolean themHoaDon (HoaDonNhapKho hoaDonNK);
	public boolean capnhatHoaDon(HoaDonNhapKho hoaDonNK);
	public List<HoaDonNhapKho> layTatCaHoaDonNhapKho();
	public HoaDonNhapKho layHoaDonNhapKhoTheoMa(String ma);
	public List<HoaDonNhapKho> layDanhSachHoaDonNhapKhoTheoNgay(String date);
	public List<HoaDonNhapKho> layDanhSachHoaDonNhapKhoTheoMaNhanVien(String maNV);
	public List<HoaDonNhapKho> layDanhSachHoaDonNhapKhoTheoTenNhanVien(String tenNV);
	public List<HoaDonNhapKho> layDanhSachHoaDonNhapKhoTheoNgay_TenNV(String date, String tenNV);
	public double tinhTongTien(String ma);
	public List<HoaDonNhapKho> layDanhSachHoaDonNhapKhoTheoTenNV_SDT_CMND(String key);
	public List<HoaDonNhapKho> layDanhSachHoaDonNhapKhoTheoTenNV_SDT_CMND_Ngay(String key, String date);
}
