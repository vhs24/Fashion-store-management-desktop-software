USE [master]
GO
/****** Object:  Database [QuanLiBanHang]    Script Date: 02/01/2023 8:11:23 CH ******/
CREATE DATABASE [QuanLiBanHang]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QuanLiBanHang', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\QuanLiBanHang.mdf' , SIZE = 20480KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QuanLiBanHang_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\QuanLiBanHang_log.ldf' , SIZE = 20480KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [QuanLiBanHang] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuanLiBanHang].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuanLiBanHang] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuanLiBanHang] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuanLiBanHang] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuanLiBanHang] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuanLiBanHang] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuanLiBanHang] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QuanLiBanHang] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuanLiBanHang] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuanLiBanHang] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuanLiBanHang] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuanLiBanHang] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuanLiBanHang] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuanLiBanHang] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuanLiBanHang] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuanLiBanHang] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QuanLiBanHang] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuanLiBanHang] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuanLiBanHang] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuanLiBanHang] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuanLiBanHang] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuanLiBanHang] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuanLiBanHang] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuanLiBanHang] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QuanLiBanHang] SET  MULTI_USER 
GO
ALTER DATABASE [QuanLiBanHang] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuanLiBanHang] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuanLiBanHang] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuanLiBanHang] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [QuanLiBanHang] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QuanLiBanHang] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [QuanLiBanHang] SET QUERY_STORE = OFF
GO
USE [QuanLiBanHang]
GO
/****** Object:  Table [dbo].[CaLamViec]    Script Date: 02/01/2023 8:11:24 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CaLamViec](
	[maCa] [varchar](255) NOT NULL,
	[buoi] [nvarchar](10) NULL,
	[ngay] [date] NULL,
	[maNhanVien] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[maCa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietHoaDonBanHang]    Script Date: 02/01/2023 8:11:24 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHoaDonBanHang](
	[soLuong] [int] NULL,
	[maSanPham] [varchar](255) NOT NULL,
	[maHoaDonBanHang] [varchar](255) NOT NULL,
	[thanhTien] [money] NULL,
PRIMARY KEY CLUSTERED 
(
	[maHoaDonBanHang] ASC,
	[maSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietHoaDonNhapKho]    Script Date: 02/01/2023 8:11:24 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHoaDonNhapKho](
	[giaNhap] [float] NULL,
	[soLuong] [int] NULL,
	[maSanPham] [varchar](255) NOT NULL,
	[maHoaDonNhapKho] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maHoaDonNhapKho] ASC,
	[maSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChuCuaHang]    Script Date: 02/01/2023 8:11:24 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChuCuaHang](
	[maChuCuaHang] [varchar](255) NOT NULL,
	[diaChi] [nvarchar](50) NULL,
	[gioiTinh] [bit] NULL,
	[ngaySinh] [date] NULL,
	[soCMND] [varchar](255) NULL,
	[soDienThoai] [varchar](255) NULL,
	[tenChuCuaHang] [nvarchar](50) NULL,
	[thanhPho] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[maChuCuaHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HangSanXuat]    Script Date: 02/01/2023 8:11:24 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HangSanXuat](
	[maHangSanXuat] [varchar](255) NOT NULL,
	[diaChi] [nvarchar](50) NULL,
	[quocGia] [nvarchar](20) NULL,
	[soDienThoai] [varchar](255) NULL,
	[tenHangSanXuat] [nvarchar](50) NULL,
	[thanhPho] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[maHangSanXuat] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDonBanHang]    Script Date: 02/01/2023 8:11:24 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDonBanHang](
	[maHoaDonBanHang] [varchar](255) NOT NULL,
	[ngayLapHoaDon] [date] NOT NULL,
	[maChuCuaHang] [varchar](255) NULL,
	[maKhachHang] [varchar](255) NULL,
	[maNhanVien] [varchar](255) NULL,
	[ghiChu] [nvarchar](50) NULL,
	[tienKhachDua] [money] NULL,
	[tongTien] [money] NULL,
	[tienTraLai] [money] NULL,
PRIMARY KEY CLUSTERED 
(
	[maHoaDonBanHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDonNhapKho]    Script Date: 02/01/2023 8:11:24 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDonNhapKho](
	[maHoaDonNhapKho] [varchar](255) NOT NULL,
	[ngayNhapKho] [date] NOT NULL,
	[maChuCuaHang] [varchar](255) NULL,
	[maNhanVien] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[maHoaDonNhapKho] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 02/01/2023 8:11:24 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[maKhachHang] [varchar](255) NOT NULL,
	[diaChi] [nvarchar](50) NULL,
	[gioiTinh] [bit] NULL,
	[ngaySinh] [date] NULL,
	[soCMND] [varchar](255) NULL,
	[soDienThoai] [varchar](255) NULL,
	[tenKhachHang] [nvarchar](50) NULL,
	[thanhPho] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[maKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiSanPham]    Script Date: 02/01/2023 8:11:24 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiSanPham](
	[maLoaiSanPham] [varchar](255) NOT NULL,
	[moTa] [nvarchar](200) NULL,
	[tenLoai] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[maLoaiSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVienBanHang]    Script Date: 02/01/2023 8:11:24 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVienBanHang](
	[maNhanVien] [varchar](255) NOT NULL,
	[diaChi] [nvarchar](50) NULL,
	[gioiTinh] [bit] NULL,
	[ngaySinh] [date] NULL,
	[ngayVaoLam] [date] NULL,
	[soCMND] [varchar](255) NULL,
	[soDienThoai] [varchar](255) NULL,
	[tenNhanVien] [nvarchar](50) NULL,
	[thanhPho] [nvarchar](20) NULL,
	[tinhTrang] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[maNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 02/01/2023 8:11:24 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[maSanPham] [varchar](255) NOT NULL,
	[giaSanPham] [float] NULL,
	[kichThuoc] [varchar](255) NULL,
	[soLuong] [int] NULL,
	[tenSanPham] [nvarchar](50) NULL,
	[maHangSanXuat] [varchar](255) NULL,
	[maLoaiSanPham] [varchar](255) NULL,
	[mauSac] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[maSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 02/01/2023 8:11:24 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[tenDangNhap] [varchar](255) NOT NULL,
	[ghiChu] [bit] NULL,
	[matKhau] [varchar](255) NULL,
	[quyen] [varchar](255) NULL,
	[maChuCuaHang] [varchar](255) NULL,
	[maNhanVien] [varchar](255) NULL,
	[tinhTrang] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[tenDangNhap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[CaLamViec] ([maCa], [buoi], [ngay], [maNhanVien]) VALUES (N'CA001', N'Sáng', CAST(N'2021-10-31' AS Date), N'NV001')
INSERT [dbo].[CaLamViec] ([maCa], [buoi], [ngay], [maNhanVien]) VALUES (N'CA002', N'Chiều', CAST(N'2021-10-31' AS Date), N'NV002')
INSERT [dbo].[CaLamViec] ([maCa], [buoi], [ngay], [maNhanVien]) VALUES (N'CA003', N'Sáng', CAST(N'2021-11-01' AS Date), N'NV003')
INSERT [dbo].[CaLamViec] ([maCa], [buoi], [ngay], [maNhanVien]) VALUES (N'CA004', N'Chiều', CAST(N'2021-11-01' AS Date), N'NV004')
GO
INSERT [dbo].[ChiTietHoaDonBanHang] ([soLuong], [maSanPham], [maHoaDonBanHang], [thanhTien]) VALUES (2, N'SP001', N'BH001', 240000.0000)
INSERT [dbo].[ChiTietHoaDonBanHang] ([soLuong], [maSanPham], [maHoaDonBanHang], [thanhTien]) VALUES (1, N'SP004', N'BH001', 95000.0000)
INSERT [dbo].[ChiTietHoaDonBanHang] ([soLuong], [maSanPham], [maHoaDonBanHang], [thanhTien]) VALUES (1, N'SP004', N'BH002', 95000.0000)
INSERT [dbo].[ChiTietHoaDonBanHang] ([soLuong], [maSanPham], [maHoaDonBanHang], [thanhTien]) VALUES (1, N'SP006', N'BH002', 300000.0000)
INSERT [dbo].[ChiTietHoaDonBanHang] ([soLuong], [maSanPham], [maHoaDonBanHang], [thanhTien]) VALUES (2, N'SP001', N'BH003', 240000.0000)
INSERT [dbo].[ChiTietHoaDonBanHang] ([soLuong], [maSanPham], [maHoaDonBanHang], [thanhTien]) VALUES (2, N'SP019', N'BH004', 190000.0000)
GO
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (100000, 20, N'SP001', N'NK001')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (90000, 10, N'SP002', N'NK001')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (100000, 30, N'SP001', N'NK002')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (90000, 10, N'SP002', N'NK002')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (450000, 5, N'SP003', N'NK003')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (950000, 1, N'SP010', N'NK003')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (120000, 2, N'SP001', N'NK004')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (100000, 3, N'SP002', N'NK004')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (100000, 6, N'SP002', N'NK005')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (75000, 10, N'SP008', N'NK006')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (85000, 3, N'SP011', N'NK007')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (245000, 19, N'SP015', N'NK007')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (65000, 1, N'SP016', N'NK007')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (245000, 1, N'SP015', N'NK008')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (65000, 18, N'SP016', N'NK008')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (300000, 2, N'SP006', N'NK009')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (75000, 3, N'SP008', N'NK009')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (550000, 9, N'SP009', N'NK010')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (950000, 4, N'SP010', N'NK010')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (550000, 1, N'SP009', N'NK011')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (250000, 1, N'SP012', N'NK011')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (120000, 2, N'SP001', N'NK012')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (95000, 1, N'SP004', N'NK013')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (950000, 3, N'SP010', N'NK013')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (250000, 1, N'SP005', N'NK014')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (550000, 5, N'SP009', N'NK014')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (250000, 9, N'SP013', N'NK014')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (125000, 45, N'SP017', N'NK015')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (300000, 1, N'SP006', N'NK016')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (250000, 1, N'SP013', N'NK016')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (550000, 1, N'SP009', N'NK017')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (245000, 1, N'SP015', N'NK017')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (65000, 1, N'SP016', N'NK017')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (125000, 25, N'SP017', N'NK017')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (250000, 25, N'SP018', N'NK018')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (250000, 1, N'SP018', N'NK019')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (250000, 1, N'SP005', N'NK020')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (950000, 1, N'SP010', N'NK020')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (250000, 12, N'SP013', N'NK020')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (125000, 1, N'SP017', N'NK020')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (120000, 1, N'SP001', N'NK021')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (85000, 7, N'SP011', N'NK021')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (245000, 1, N'SP015', N'NK021')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (85000, 15, N'SP020', N'NK022')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (45000, 35, N'SP021', N'NK023')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (250000, 8, N'SP012', N'NK024')
INSERT [dbo].[ChiTietHoaDonNhapKho] ([giaNhap], [soLuong], [maSanPham], [maHoaDonNhapKho]) VALUES (75000, 1, N'SP014', N'NK024')
GO
INSERT [dbo].[ChuCuaHang] ([maChuCuaHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenChuCuaHang], [thanhPho]) VALUES (N'CCH001', N'101 QL 13 P24 Bình Thạnh', 0, CAST(N'1990-03-26' AS Date), N'204567849', N'0946327458', N'Võ Hoàng Sơn', N'Hồ Chí Minh')
GO
INSERT [dbo].[HangSanXuat] ([maHangSanXuat], [diaChi], [quocGia], [soDienThoai], [tenHangSanXuat], [thanhPho]) VALUES (N'HSX001', N'27a đường số 22 KP 4 Thủ Đức', N'Việt Nam', N'0389546758', N'Việt Tiến', N'Hồ Chí Minh')
INSERT [dbo].[HangSanXuat] ([maHangSanXuat], [diaChi], [quocGia], [soDienThoai], [tenHangSanXuat], [thanhPho]) VALUES (N'HSX002', N'106 Lê Văn Sỹ P 11 Phú Nhuân.', N'Việt Nam', N'(028) 3991 9759', N'Nike Lê Văn Sỹ', N'Hồ Chí Minh')
INSERT [dbo].[HangSanXuat] ([maHangSanXuat], [diaChi], [quocGia], [soDienThoai], [tenHangSanXuat], [thanhPho]) VALUES (N'HSX003', N'', N'Việt Nam', N'0145235269', N'A Nam', N'Long An')
INSERT [dbo].[HangSanXuat] ([maHangSanXuat], [diaChi], [quocGia], [soDienThoai], [tenHangSanXuat], [thanhPho]) VALUES (N'HSX004', NULL, N'Pháp', N'(038) 898 232', N'Nike', N'Pari')
INSERT [dbo].[HangSanXuat] ([maHangSanXuat], [diaChi], [quocGia], [soDienThoai], [tenHangSanXuat], [thanhPho]) VALUES (N'HSX005', NULL, N'Việt Nam', N'0541236523', N'Trường Sa', N'Bình Dương')
INSERT [dbo].[HangSanXuat] ([maHangSanXuat], [diaChi], [quocGia], [soDienThoai], [tenHangSanXuat], [thanhPho]) VALUES (N'HSX006', NULL, N'Pháp', N'0423521265', N'Chanel', N'Pari')
INSERT [dbo].[HangSanXuat] ([maHangSanXuat], [diaChi], [quocGia], [soDienThoai], [tenHangSanXuat], [thanhPho]) VALUES (N'HSX007', NULL, N'Pháp', N'0741242562', N'Hermès', N'Pari')
INSERT [dbo].[HangSanXuat] ([maHangSanXuat], [diaChi], [quocGia], [soDienThoai], [tenHangSanXuat], [thanhPho]) VALUES (N'HSX008', NULL, N'Ý', N'0412354121', N'Gucci', N'Roma')
INSERT [dbo].[HangSanXuat] ([maHangSanXuat], [diaChi], [quocGia], [soDienThoai], [tenHangSanXuat], [thanhPho]) VALUES (N'HSX009', NULL, N'Pháp', N'(038) 254 2541', N'Louis Vuitton', N'Pari')
INSERT [dbo].[HangSanXuat] ([maHangSanXuat], [diaChi], [quocGia], [soDienThoai], [tenHangSanXuat], [thanhPho]) VALUES (N'HSX010', NULL, N'Ý', N'(044) 404 4004', N'Prada', NULL)
INSERT [dbo].[HangSanXuat] ([maHangSanXuat], [diaChi], [quocGia], [soDienThoai], [tenHangSanXuat], [thanhPho]) VALUES (N'HSX011', N'', N'Ý', N'(038) 628 4412', N'Loggi', N'Roma')
INSERT [dbo].[HangSanXuat] ([maHangSanXuat], [diaChi], [quocGia], [soDienThoai], [tenHangSanXuat], [thanhPho]) VALUES (N'HSX012', N'', N'Ý', N'0385421245', N'Loogi', N'Roma')
INSERT [dbo].[HangSanXuat] ([maHangSanXuat], [diaChi], [quocGia], [soDienThoai], [tenHangSanXuat], [thanhPho]) VALUES (N'HSX013', N'', N'Pháp', N'084521236', N'Addidas', N'Paris')
INSERT [dbo].[HangSanXuat] ([maHangSanXuat], [diaChi], [quocGia], [soDienThoai], [tenHangSanXuat], [thanhPho]) VALUES (N'HSX014', N'', N'Ý', N'04563255', N'Adcan', N'Roma')
INSERT [dbo].[HangSanXuat] ([maHangSanXuat], [diaChi], [quocGia], [soDienThoai], [tenHangSanXuat], [thanhPho]) VALUES (N'HSX015', N'', N'Nhật bản', N'0945235', N'Exrend', N'Tokyo')
INSERT [dbo].[HangSanXuat] ([maHangSanXuat], [diaChi], [quocGia], [soDienThoai], [tenHangSanXuat], [thanhPho]) VALUES (N'HSX016', N'', N'Pháp', N'01254898', N'Unitred', N'Paris')
GO
INSERT [dbo].[HoaDonBanHang] ([maHoaDonBanHang], [ngayLapHoaDon], [maChuCuaHang], [maKhachHang], [maNhanVien], [ghiChu], [tienKhachDua], [tongTien], [tienTraLai]) VALUES (N'BH001', CAST(N'2023-01-02' AS Date), N'CCH001', N'KH004', N'CCH001', N'Mua mới', 350000.0000, 335000.0000, 15000.0000)
INSERT [dbo].[HoaDonBanHang] ([maHoaDonBanHang], [ngayLapHoaDon], [maChuCuaHang], [maKhachHang], [maNhanVien], [ghiChu], [tienKhachDua], [tongTien], [tienTraLai]) VALUES (N'BH002', CAST(N'2023-01-02' AS Date), N'CCH001', N'KH001', N'CCH001', N'Mua mới', 400000.0000, 395000.0000, 5000.0000)
INSERT [dbo].[HoaDonBanHang] ([maHoaDonBanHang], [ngayLapHoaDon], [maChuCuaHang], [maKhachHang], [maNhanVien], [ghiChu], [tienKhachDua], [tongTien], [tienTraLai]) VALUES (N'BH003', CAST(N'2023-01-02' AS Date), N'CCH001', N'KH014', N'NV001', N'Mua mới', 500000.0000, 240000.0000, 260000.0000)
INSERT [dbo].[HoaDonBanHang] ([maHoaDonBanHang], [ngayLapHoaDon], [maChuCuaHang], [maKhachHang], [maNhanVien], [ghiChu], [tienKhachDua], [tongTien], [tienTraLai]) VALUES (N'BH004', CAST(N'2023-01-02' AS Date), N'CCH001', N'KH027', N'NV002', N'Mua mới', 200000.0000, 190000.0000, 10000.0000)
GO
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK001', CAST(N'2021-01-01' AS Date), N'CCH001', N'NV001')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK002', CAST(N'2021-02-01' AS Date), N'CCH001', N'NV006')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK003', CAST(N'2021-05-14' AS Date), N'CCH001', N'NV005')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK004', CAST(N'2021-06-14' AS Date), N'CCH001', N'NV001')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK005', CAST(N'2021-07-16' AS Date), N'CCH001', N'NV004')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK006', CAST(N'2021-08-16' AS Date), N'CCH001', N'NV003')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK007', CAST(N'2021-10-16' AS Date), N'CCH001', N'NV002')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK008', CAST(N'2021-11-17' AS Date), N'CCH001', N'NV001')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK009', CAST(N'2021-11-17' AS Date), N'CCH001', N'NV008')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK010', CAST(N'2021-11-17' AS Date), N'CCH001', N'NV005')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK011', CAST(N'2021-11-17' AS Date), N'CCH001', N'NV007')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK012', CAST(N'2021-11-17' AS Date), N'CCH001', N'NV006')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK013', CAST(N'2021-11-29' AS Date), N'CCH001', N'CCH001')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK014', CAST(N'2021-11-29' AS Date), N'CCH001', N'CCH001')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK015', CAST(N'2021-11-29' AS Date), N'CCH001', N'CCH001')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK016', CAST(N'2021-11-29' AS Date), N'CCH001', N'CCH001')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK017', CAST(N'2021-11-29' AS Date), N'CCH001', N'NV003')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK018', CAST(N'2021-11-29' AS Date), N'CCH001', N'CCH001')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK019', CAST(N'2021-11-29' AS Date), N'CCH001', N'CCH001')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK020', CAST(N'2021-11-29' AS Date), N'CCH001', N'CCH001')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK021', CAST(N'2021-11-29' AS Date), N'CCH001', N'CCH001')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK022', CAST(N'2021-11-30' AS Date), N'CCH001', N'CCH001')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK023', CAST(N'2021-11-30' AS Date), N'CCH001', N'CCH001')
INSERT [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho], [ngayNhapKho], [maChuCuaHang], [maNhanVien]) VALUES (N'NK024', CAST(N'2021-11-30' AS Date), N'CCH001', N'CCH001')
GO
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH001', N'47 Điện Biên Phủ P12 Bình Thạnh', 1, CAST(N'1997-01-12' AS Date), N'21345268739', N'0974256437', N'Lê Hoài Chi', N'Hồ Chí Minh')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH002', N'12 Nguyễn Văn Bảo P3 Gò Vấp', 1, CAST(N'1997-01-12' AS Date), N'21452456713', N'0323415367', N'Trần Thị My', N'Hồ Chí Minh')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH003', N'12, Phan Chu Trinh', 0, CAST(N'2004-04-12' AS Date), N'074232524512', N'0142542526', N'Lê Mỹ Châu', N'Hồ Chí Minh')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH004', N'', 0, CAST(N'2000-12-01' AS Date), N'124567214589', N'0123456789', N'Lê Nam ', N'Bình Dương')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH005', N'23, Quang Trung', 0, CAST(N'2011-08-12' AS Date), N'071201005479', N'0386254125', N'Trần Nam Thế', N'Hồ Chí Minh')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH006', N'', 0, CAST(N'2000-04-12' AS Date), N'074214235648', N'0907452365', N'Trần Long Nhật', N'Hồ Chí Minh')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH007', N'', 0, CAST(N'1996-09-15' AS Date), N'074125325641', N'0325621542', N'Trần Nam Tiến', N'Hồ Chí Minh')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH008', N'89 Trương Định', 0, CAST(N'2003-08-04' AS Date), N'072325211452', N'0332542580', N'Vũ Nam Định', N'Hồ Chí Minh')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH009', N'', 0, CAST(N'2001-04-25' AS Date), N'072245623658', N'0237854123', N'Trần Vĩnh Nam', N'Bình Dương')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH010', N'', 0, CAST(N'1993-11-07' AS Date), N'074125621452', N'0456825222', N'Lê Vĩnh', N'Hải Dương')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH011', N'', 0, CAST(N'2012-11-02' AS Date), N'078425422222', N'0324585421', N'Trần Long Vĩnh', N'Nam Định')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH012', N'', 0, CAST(N'1994-06-07' AS Date), N'023542145212', N'0423546254', N'Phan Trọng Nam', N'Hồ Chí Minh')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH013', N'', 0, CAST(N'1997-06-10' AS Date), N'074232561245', N'0852142365', N'Trần Văn Sỹ', N'Hồ Chí Minh')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH014', NULL, 1, CAST(N'1994-07-10' AS Date), N'032112541211', N'0412425214', N'Trần Minh Phương', N'Bình Dương')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH015', NULL, 1, CAST(N'1999-05-14' AS Date), N'072412523652', N'0145241014', N'Lương Cao Bảo', N'Bình Dương')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH016', N'', 0, CAST(N'2014-08-17' AS Date), N'075241215235', N'0235468547', N'Lê thành Tâm', N'Hồ Chí Minh')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH017', N'', 0, CAST(N'2013-04-06' AS Date), N'074124540125', N'0452354121', N'A Nam', N'Long An')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH018', N'', 0, CAST(N'2016-07-09' AS Date), N'041252412532', N'0945652478', N'Long Đáo', N'Long An')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH019', N'', 0, CAST(N'1998-04-07' AS Date), N'074152312542', N'0945321254', N'Trần Phạm', N'Long An')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH020', N'', 0, CAST(N'2003-08-15' AS Date), N'072142352652', N'0865231471', N'Lê Nam', N'Bình Dương')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH021', N'', 0, CAST(N'2003-08-15' AS Date), N'072142352650', N'0865231470', N'Lê Nam', N'Bình Dương')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH022', N'', 0, CAST(N'2001-07-23' AS Date), N'074125325', N'0124587451', N'Lê Trọng Nhân', N'Long An')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH023', N'', 0, CAST(N'2022-05-17' AS Date), N'045621325456', N'0908523652', N'Lê Chí Hiếu', N'Hồ Chí Minh')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH024', N'', 0, CAST(N'2004-03-29' AS Date), N'014523521234', N'07412213256', N'Nguyễn Đình Bảo', N'Tây Ninh')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH025', N'', 0, CAST(N'2021-04-22' AS Date), N'041234572212', N'0987452121', N'Nguyễn Minh Thiện', N'Tây Ninh')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH026', N'', 0, CAST(N'2010-08-27' AS Date), N'078965321423', N'2398563251', N'Nguyễn Văn A', N'Hồ Chí Minh')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH027', N'', 1, CAST(N'2000-08-23' AS Date), N'074123562536', N'0978536652', N'Trần Phước Đình Nam', N'Tây Ninh')
INSERT [dbo].[KhachHang] ([maKhachHang], [diaChi], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [tenKhachHang], [thanhPho]) VALUES (N'KH028', N'', 0, CAST(N'2000-09-04' AS Date), N'074125423698', N'0475236542', N'Cao A Nam', N'Tây Ninh')
GO
INSERT [dbo].[LoaiSanPham] ([maLoaiSanPham], [moTa], [tenLoai]) VALUES (N'L001', N'', N'Quần')
INSERT [dbo].[LoaiSanPham] ([maLoaiSanPham], [moTa], [tenLoai]) VALUES (N'L002', N'', N'Áo')
INSERT [dbo].[LoaiSanPham] ([maLoaiSanPham], [moTa], [tenLoai]) VALUES (N'L003', N'', N'Mũ')
INSERT [dbo].[LoaiSanPham] ([maLoaiSanPham], [moTa], [tenLoai]) VALUES (N'L004', N'', N'Giày da')
INSERT [dbo].[LoaiSanPham] ([maLoaiSanPham], [moTa], [tenLoai]) VALUES (N'L005', N'', N'Giày thể thao')
INSERT [dbo].[LoaiSanPham] ([maLoaiSanPham], [moTa], [tenLoai]) VALUES (N'L006', N'', N'Nón')
INSERT [dbo].[LoaiSanPham] ([maLoaiSanPham], [moTa], [tenLoai]) VALUES (N'L007', N'', N'Dây nịch')
INSERT [dbo].[LoaiSanPham] ([maLoaiSanPham], [moTa], [tenLoai]) VALUES (N'L008', N'', N'Đồ văn phòng')
INSERT [dbo].[LoaiSanPham] ([maLoaiSanPham], [moTa], [tenLoai]) VALUES (N'L009', N'', N'Áo đas banh')
INSERT [dbo].[LoaiSanPham] ([maLoaiSanPham], [moTa], [tenLoai]) VALUES (N'L010', N'', N'Giày leo núi')
GO
INSERT [dbo].[NhanVienBanHang] ([maNhanVien], [diaChi], [gioiTinh], [ngaySinh], [ngayVaoLam], [soCMND], [soDienThoai], [tenNhanVien], [thanhPho], [tinhTrang]) VALUES (N'CCH001', N'', 0, CAST(N'1990-01-01' AS Date), CAST(N'2020-01-20' AS Date), N'014521212562', N'0214523695', N'Võ Hoàng Sơn', N'Hồ Chí Minh', 1)
INSERT [dbo].[NhanVienBanHang] ([maNhanVien], [diaChi], [gioiTinh], [ngaySinh], [ngayVaoLam], [soCMND], [soDienThoai], [tenNhanVien], [thanhPho], [tinhTrang]) VALUES (N'NV001', N'70/4 Xô Viết Nghệ Tĩnh P21', 0, CAST(N'2021-11-01' AS Date), CAST(N'2019-11-19' AS Date), N'214574961894', N'0962855956', N'Lê Mạnh An', N'Hồ Chí Minh', 1)
INSERT [dbo].[NhanVienBanHang] ([maNhanVien], [diaChi], [gioiTinh], [ngaySinh], [ngayVaoLam], [soCMND], [soDienThoai], [tenNhanVien], [thanhPho], [tinhTrang]) VALUES (N'NV002', N'280 /70/76 Bùi Hữu Nghĩa P2', 1, CAST(N'2000-04-23' AS Date), CAST(N'2021-11-01' AS Date), N'223456978', N'0346937445', N'Huỳnh Bích Trâm', N'Hồ Chí Minh', 1)
INSERT [dbo].[NhanVienBanHang] ([maNhanVien], [diaChi], [gioiTinh], [ngaySinh], [ngayVaoLam], [soCMND], [soDienThoai], [tenNhanVien], [thanhPho], [tinhTrang]) VALUES (N'NV003', N'224/2 Tran Ke Xuong P7 Phu Nhuan', 0, CAST(N'2021-11-01' AS Date), CAST(N'2021-09-25' AS Date), N'2245869080', N'0962733586', N'Le Hoai Nhat', N'Ho Chi Minh', 1)
INSERT [dbo].[NhanVienBanHang] ([maNhanVien], [diaChi], [gioiTinh], [ngaySinh], [ngayVaoLam], [soCMND], [soDienThoai], [tenNhanVien], [thanhPho], [tinhTrang]) VALUES (N'NV004', N'39/6 Lê Trực P7 Bình Thạnh', 0, CAST(N'1997-12-12' AS Date), CAST(N'2021-11-01' AS Date), N'021853495745', N'0974233846', N'Huỳnh Anh Thi', N'Hồ Chí Minh', 1)
INSERT [dbo].[NhanVienBanHang] ([maNhanVien], [diaChi], [gioiTinh], [ngaySinh], [ngayVaoLam], [soCMND], [soDienThoai], [tenNhanVien], [thanhPho], [tinhTrang]) VALUES (N'NV005', N'', 0, CAST(N'2001-11-13' AS Date), CAST(N'2021-11-02' AS Date), N'74152146241', N'0254786942', N'Lê Hoàng Nam', N'Bình Định', 1)
INSERT [dbo].[NhanVienBanHang] ([maNhanVien], [diaChi], [gioiTinh], [ngaySinh], [ngayVaoLam], [soCMND], [soDienThoai], [tenNhanVien], [thanhPho], [tinhTrang]) VALUES (N'NV006', N'', 0, CAST(N'1994-06-06' AS Date), CAST(N'2021-11-01' AS Date), N'074125326589', N'0234789521', N'Lâm Minh Triết', N'Hồ Chí Minh', 1)
INSERT [dbo].[NhanVienBanHang] ([maNhanVien], [diaChi], [gioiTinh], [ngaySinh], [ngayVaoLam], [soCMND], [soDienThoai], [tenNhanVien], [thanhPho], [tinhTrang]) VALUES (N'NV007', N'', 0, CAST(N'2000-11-14' AS Date), CAST(N'2021-11-02' AS Date), N'072356428562', N'0907453256', N'Nguyễn Đình Công', N'Bình Dương', 0)
INSERT [dbo].[NhanVienBanHang] ([maNhanVien], [diaChi], [gioiTinh], [ngaySinh], [ngayVaoLam], [soCMND], [soDienThoai], [tenNhanVien], [thanhPho], [tinhTrang]) VALUES (N'NV008', N'', 0, CAST(N'2000-11-04' AS Date), CAST(N'2020-05-04' AS Date), N'094236521475', N'0756232567', N'Nguyễn Nam', N'Long An', 1)
INSERT [dbo].[NhanVienBanHang] ([maNhanVien], [diaChi], [gioiTinh], [ngaySinh], [ngayVaoLam], [soCMND], [soDienThoai], [tenNhanVien], [thanhPho], [tinhTrang]) VALUES (N'NV009', N'', 0, CAST(N'2002-05-06' AS Date), CAST(N'2021-10-05' AS Date), N'0345231235124', N'0325632253', N'Trương Thanh Vinh', N'Long An', 1)
GO
INSERT [dbo].[SanPham] ([maSanPham], [giaSanPham], [kichThuoc], [soLuong], [tenSanPham], [maHangSanXuat], [maLoaiSanPham], [mauSac]) VALUES (N'SP001', 120000, N'XL', 32, N'Quần Jean', N'HSX002', N'L001', N'Xanh Dương')
INSERT [dbo].[SanPham] ([maSanPham], [giaSanPham], [kichThuoc], [soLuong], [tenSanPham], [maHangSanXuat], [maLoaiSanPham], [mauSac]) VALUES (N'SP002', 10000, N'XL', 24, N'Áo sơ mi', N'HSX001', N'L002', N'Đen ')
INSERT [dbo].[SanPham] ([maSanPham], [giaSanPham], [kichThuoc], [soLuong], [tenSanPham], [maHangSanXuat], [maLoaiSanPham], [mauSac]) VALUES (N'SP003', 450000, N'S', 34, N'Quần tây', N'HSX001', N'L001', N'Đỏ')
INSERT [dbo].[SanPham] ([maSanPham], [giaSanPham], [kichThuoc], [soLuong], [tenSanPham], [maHangSanXuat], [maLoaiSanPham], [mauSac]) VALUES (N'SP004', 95000, N'S', 7, N'Áo thun', N'HSX001', N'L002', N'Đỏ')
INSERT [dbo].[SanPham] ([maSanPham], [giaSanPham], [kichThuoc], [soLuong], [tenSanPham], [maHangSanXuat], [maLoaiSanPham], [mauSac]) VALUES (N'SP005', 250000, N'XL', 14, N'Áo thun nam', N'HSX004', N'L002', N'Đỏ')
INSERT [dbo].[SanPham] ([maSanPham], [giaSanPham], [kichThuoc], [soLuong], [tenSanPham], [maHangSanXuat], [maLoaiSanPham], [mauSac]) VALUES (N'SP006', 300000, N'M', 17, N'Quần thun Nike', N'HSX001', N'L001', N'Trắng')
INSERT [dbo].[SanPham] ([maSanPham], [giaSanPham], [kichThuoc], [soLuong], [tenSanPham], [maHangSanXuat], [maLoaiSanPham], [mauSac]) VALUES (N'SP007', 50000, N'XL', 12, N'Áo văn phòng nam', N'HSX001', N'L002', N'Trắng ')
INSERT [dbo].[SanPham] ([maSanPham], [giaSanPham], [kichThuoc], [soLuong], [tenSanPham], [maHangSanXuat], [maLoaiSanPham], [mauSac]) VALUES (N'SP008', 75000, N'XL', 14, N'Quần leo núi ', N'HSX001', N'L001', N'Đen')
INSERT [dbo].[SanPham] ([maSanPham], [giaSanPham], [kichThuoc], [soLuong], [tenSanPham], [maHangSanXuat], [maLoaiSanPham], [mauSac]) VALUES (N'SP009', 550000, N'XXL', 30, N'Áo thể thao', N'HSX001', N'L001', N'Đỏ')
INSERT [dbo].[SanPham] ([maSanPham], [giaSanPham], [kichThuoc], [soLuong], [tenSanPham], [maHangSanXuat], [maLoaiSanPham], [mauSac]) VALUES (N'SP010', 950000, N'XL', 28, N'Áo thể thao leo núi Nam', N'HSX001', N'L001', N'Cam')
INSERT [dbo].[SanPham] ([maSanPham], [giaSanPham], [kichThuoc], [soLuong], [tenSanPham], [maHangSanXuat], [maLoaiSanPham], [mauSac]) VALUES (N'SP011', 85000, N'XL', 26, N'Quần tây văn phòng nam', N'HSX001', N'L001', N'Xanh Dương')
INSERT [dbo].[SanPham] ([maSanPham], [giaSanPham], [kichThuoc], [soLuong], [tenSanPham], [maHangSanXuat], [maLoaiSanPham], [mauSac]) VALUES (N'SP012', 250000, N'L', 20, N'Quần kaki nam', N'HSX001', N'L001', N'Vàng')
INSERT [dbo].[SanPham] ([maSanPham], [giaSanPham], [kichThuoc], [soLuong], [tenSanPham], [maHangSanXuat], [maLoaiSanPham], [mauSac]) VALUES (N'SP013', 250000, N'XXL', 37, N'Áo mưa bảo túi', N'HSX003', N'L002', N'Đỏ')
INSERT [dbo].[SanPham] ([maSanPham], [giaSanPham], [kichThuoc], [soLuong], [tenSanPham], [maHangSanXuat], [maLoaiSanPham], [mauSac]) VALUES (N'SP014', 75000, N'XXXL', 29, N'Áo mưa chống thấm', N'HSX001', N'L001', N'Vàng')
INSERT [dbo].[SanPham] ([maSanPham], [giaSanPham], [kichThuoc], [soLuong], [tenSanPham], [maHangSanXuat], [maLoaiSanPham], [mauSac]) VALUES (N'SP015', 245000, N'L', 79, N'Áo thể theo MC', N'HSX001', N'L001', N'Xanh Dương')
INSERT [dbo].[SanPham] ([maSanPham], [giaSanPham], [kichThuoc], [soLuong], [tenSanPham], [maHangSanXuat], [maLoaiSanPham], [mauSac]) VALUES (N'SP016', 65000, N'L', 33, N'Áo thun 3 lổ nam', N'HSX003', N'L002', N'Xanh Dương')
INSERT [dbo].[SanPham] ([maSanPham], [giaSanPham], [kichThuoc], [soLuong], [tenSanPham], [maHangSanXuat], [maLoaiSanPham], [mauSac]) VALUES (N'SP017', 125000, N'XL', 59, N'Áo len lông báo', N'HSX003', N'L002', N'Vàng')
INSERT [dbo].[SanPham] ([maSanPham], [giaSanPham], [kichThuoc], [soLuong], [tenSanPham], [maHangSanXuat], [maLoaiSanPham], [mauSac]) VALUES (N'SP018', 250000, N'XXL', 51, N'Quần đùi nam', N'HSX001', N'L001', N'Đỏ')
INSERT [dbo].[SanPham] ([maSanPham], [giaSanPham], [kichThuoc], [soLuong], [tenSanPham], [maHangSanXuat], [maLoaiSanPham], [mauSac]) VALUES (N'SP019', 95000, N'XL', 33, N'Áo ba lỗ màu thường', N'HSX001', N'L002', N'Xanh Dương')
INSERT [dbo].[SanPham] ([maSanPham], [giaSanPham], [kichThuoc], [soLuong], [tenSanPham], [maHangSanXuat], [maLoaiSanPham], [mauSac]) VALUES (N'SP020', 85000, N'S', 30, N'Nón thời trang nam', N'HSX001', N'L006', N'Đỏ')
INSERT [dbo].[SanPham] ([maSanPham], [giaSanPham], [kichThuoc], [soLuong], [tenSanPham], [maHangSanXuat], [maLoaiSanPham], [mauSac]) VALUES (N'SP021', 45000, N'S', 70, N'Áo ba lỗ đỏ', N'HSX001', N'L002', N'Đỏ')
GO
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [ghiChu], [matKhau], [quyen], [maChuCuaHang], [maNhanVien], [tinhTrang]) VALUES (N'CCH001', 1, N'987654', N'ChuCuaHang', N'CCH001', N'CCH001', 0)
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [ghiChu], [matKhau], [quyen], [maChuCuaHang], [maNhanVien], [tinhTrang]) VALUES (N'NV001', 1, N'123456789', N'Nhân viên', N'CCH001', N'NV001', 0)
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [ghiChu], [matKhau], [quyen], [maChuCuaHang], [maNhanVien], [tinhTrang]) VALUES (N'NV002', 1, N'123456789', N'NhA¢n viAªn', N'CCH001', N'NV002', 1)
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [ghiChu], [matKhau], [quyen], [maChuCuaHang], [maNhanVien], [tinhTrang]) VALUES (N'NV003', 1, N'123456', N'NhA¢n viAªn', N'CCH001', N'NV003', 0)
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [ghiChu], [matKhau], [quyen], [maChuCuaHang], [maNhanVien], [tinhTrang]) VALUES (N'NV004', 1, N'123456', N'NhA¢n viAªn', N'CCH001', N'NV004', 0)
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [ghiChu], [matKhau], [quyen], [maChuCuaHang], [maNhanVien], [tinhTrang]) VALUES (N'NV005', 1, N'123456789', N'NhanVien', N'CCH001', N'NV005', 0)
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [ghiChu], [matKhau], [quyen], [maChuCuaHang], [maNhanVien], [tinhTrang]) VALUES (N'NV006', 1, N'123456', N'NhanVien', N'CCH001', N'NV006', 0)
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [ghiChu], [matKhau], [quyen], [maChuCuaHang], [maNhanVien], [tinhTrang]) VALUES (N'NV007', 1, N'123456', N'NhanVien', N'CCH001', N'NV007', 0)
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [ghiChu], [matKhau], [quyen], [maChuCuaHang], [maNhanVien], [tinhTrang]) VALUES (N'NV008', 1, N'1111', N'NhanVien', N'CCH001', N'NV008', 0)
GO
ALTER TABLE [dbo].[CaLamViec]  WITH CHECK ADD  CONSTRAINT [FK4ggjel5t8l9lx4hxg9i63q2er] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVienBanHang] ([maNhanVien])
GO
ALTER TABLE [dbo].[CaLamViec] CHECK CONSTRAINT [FK4ggjel5t8l9lx4hxg9i63q2er]
GO
ALTER TABLE [dbo].[ChiTietHoaDonBanHang]  WITH CHECK ADD  CONSTRAINT [FK51c7whthveloo78utuqw3112u] FOREIGN KEY([maSanPham])
REFERENCES [dbo].[SanPham] ([maSanPham])
GO
ALTER TABLE [dbo].[ChiTietHoaDonBanHang] CHECK CONSTRAINT [FK51c7whthveloo78utuqw3112u]
GO
ALTER TABLE [dbo].[ChiTietHoaDonBanHang]  WITH CHECK ADD  CONSTRAINT [FKhje61l283c6na54yl19b466mc] FOREIGN KEY([maHoaDonBanHang])
REFERENCES [dbo].[HoaDonBanHang] ([maHoaDonBanHang])
GO
ALTER TABLE [dbo].[ChiTietHoaDonBanHang] CHECK CONSTRAINT [FKhje61l283c6na54yl19b466mc]
GO
ALTER TABLE [dbo].[ChiTietHoaDonNhapKho]  WITH CHECK ADD  CONSTRAINT [FKdpreks21kq8u7e2gor2psmxey] FOREIGN KEY([maHoaDonNhapKho])
REFERENCES [dbo].[HoaDonNhapKho] ([maHoaDonNhapKho])
GO
ALTER TABLE [dbo].[ChiTietHoaDonNhapKho] CHECK CONSTRAINT [FKdpreks21kq8u7e2gor2psmxey]
GO
ALTER TABLE [dbo].[ChiTietHoaDonNhapKho]  WITH CHECK ADD  CONSTRAINT [FKsm0151tvhbrfv24os533tl3m4] FOREIGN KEY([maSanPham])
REFERENCES [dbo].[SanPham] ([maSanPham])
GO
ALTER TABLE [dbo].[ChiTietHoaDonNhapKho] CHECK CONSTRAINT [FKsm0151tvhbrfv24os533tl3m4]
GO
ALTER TABLE [dbo].[HoaDonBanHang]  WITH CHECK ADD  CONSTRAINT [FKhojl6t1vmcx68mbgl25l6ym4a] FOREIGN KEY([maChuCuaHang])
REFERENCES [dbo].[ChuCuaHang] ([maChuCuaHang])
GO
ALTER TABLE [dbo].[HoaDonBanHang] CHECK CONSTRAINT [FKhojl6t1vmcx68mbgl25l6ym4a]
GO
ALTER TABLE [dbo].[HoaDonBanHang]  WITH CHECK ADD  CONSTRAINT [FKo7uugcoyvu2eo8lar43d8dw10] FOREIGN KEY([maKhachHang])
REFERENCES [dbo].[KhachHang] ([maKhachHang])
GO
ALTER TABLE [dbo].[HoaDonBanHang] CHECK CONSTRAINT [FKo7uugcoyvu2eo8lar43d8dw10]
GO
ALTER TABLE [dbo].[HoaDonBanHang]  WITH CHECK ADD  CONSTRAINT [FKqj29jnt3qd04q0895hggg0srb] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVienBanHang] ([maNhanVien])
GO
ALTER TABLE [dbo].[HoaDonBanHang] CHECK CONSTRAINT [FKqj29jnt3qd04q0895hggg0srb]
GO
ALTER TABLE [dbo].[HoaDonNhapKho]  WITH CHECK ADD  CONSTRAINT [FK3yq6j9ag2s0ftqf538bps27bw] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVienBanHang] ([maNhanVien])
GO
ALTER TABLE [dbo].[HoaDonNhapKho] CHECK CONSTRAINT [FK3yq6j9ag2s0ftqf538bps27bw]
GO
ALTER TABLE [dbo].[HoaDonNhapKho]  WITH CHECK ADD  CONSTRAINT [FK80fr5x9can9tk0ele1idx158n] FOREIGN KEY([maChuCuaHang])
REFERENCES [dbo].[ChuCuaHang] ([maChuCuaHang])
GO
ALTER TABLE [dbo].[HoaDonNhapKho] CHECK CONSTRAINT [FK80fr5x9can9tk0ele1idx158n]
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [FK7919bjx48denbcw9d7vbophpq] FOREIGN KEY([maHangSanXuat])
REFERENCES [dbo].[HangSanXuat] ([maHangSanXuat])
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [FK7919bjx48denbcw9d7vbophpq]
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [FKbvqxsijsbvtwuleneul95jswa] FOREIGN KEY([maLoaiSanPham])
REFERENCES [dbo].[LoaiSanPham] ([maLoaiSanPham])
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [FKbvqxsijsbvtwuleneul95jswa]
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [FK45fks2rf4fepa1ka2vd7102ni] FOREIGN KEY([maChuCuaHang])
REFERENCES [dbo].[ChuCuaHang] ([maChuCuaHang])
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [FK45fks2rf4fepa1ka2vd7102ni]
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [FKcfjfwytd2o6jfw5a9xo892onr] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVienBanHang] ([maNhanVien])
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [FKcfjfwytd2o6jfw5a9xo892onr]
GO
USE [master]
GO
ALTER DATABASE [QuanLiBanHang] SET  READ_WRITE 
GO
