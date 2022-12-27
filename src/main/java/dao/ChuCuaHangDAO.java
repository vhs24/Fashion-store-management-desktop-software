package dao;

import entity.ChuCuaHang;

public interface ChuCuaHangDAO {
	public ChuCuaHang layThongTinChuCuaHang();
	public boolean capnhatThongTinChuCuaHang();
	public ChuCuaHang layThongTinChuCuaHangTheoMa(String ma);
}
