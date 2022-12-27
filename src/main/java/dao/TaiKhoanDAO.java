package dao;

import java.util.List;

import entity.TaiKhoan;

public interface TaiKhoanDAO {
	public boolean themTaiKhoan(TaiKhoan taiKhoan);
	public boolean capnhatTrangThai(TaiKhoan taiKhoan);
	public boolean cpanhatTaiKhoan(TaiKhoan taikhoan); 
	public boolean capNhatMatKhau(String tenDangNhap, String newPass);
	public int  kiemTraTaiKhoan(String tenDangNhap, String matKhau);
	boolean kiemtraTinhTrangDangNhap(Boolean tinhTrang, String tenDangNhap);
	public TaiKhoan layThongTinTaiKhoanTheoTenDangNhap(String ma);
	public TaiKhoan layThongTinTaiKhoanTheoTrangThai(boolean tinhTrang);
	public List<TaiKhoan> layDanhSachTaiKhoan();
}
