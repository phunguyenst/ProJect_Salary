USE [master]
GO
/****** Object:  Database [QuanLyLuongSanPham]    Script Date: 7/5/2016 11:35:56 PM ******/
CREATE DATABASE [QuanLyLuongSanPham]
 CONTAINMENT = NONE
 go
ALTER DATABASE [QuanLyLuongSanPham] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuanLyLuongSanPham].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuanLyLuongSanPham] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET RECOVERY FULL 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET  MULTI_USER 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuanLyLuongSanPham] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'QuanLyLuongSanPham', N'ON'
GO
USE [QuanLyLuongSanPham]
GO
/****** Object:  Table [dbo].[LuongCongNhan]    Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[LuongCongNhan](
    [MaLuong] [nvarchar](255) NOT NULL,
	[MaPB] [nvarchar](255) NOT NULL,
	[MaPhieu] [nvarchar](255) NOT NULL,
	[SoSPLamDuoc] [int] NOT NULL,
	[PhuCap] [int] NOT NULL,
	[ThangLuong] [int] NOT NULL,
	[NamLuong] [int] NOT NULL,
	[MaPhanCong] [nvarchar](255) NOT NULL,
	[MaCa] [nvarchar](255) NOT NULL,
	
) 

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Congnhan]  Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Congnhan](
	[MaCN] [nvarchar](255) NOT NULL,
	[MaPB] [nvarchar](255) NOT NULL,
	[TenCN] [nvarchar](255) NOT NULL,
	[Sđt] [nvarchar](255) NOT NULL,
	[DiaChi] [nvarchar](255) NOT NULL,
	[GioiTinh] [nvarchar](255) NOT NULL,
	[NgaySinh] [date] NOT NULL,
) 

GO
/****** Object:  Table [dbo].[PhieuCong_CN]    Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuCong_CN](
    [MaPhieu] [nvarchar](255) NOT NULL,
    [MaCa] [nvarchar](255) NOT NULL,
	[TenCN] [nvarchar](255) NOT NULL,
	[SoSPChamCong] [int] NOT NULL,
	[MaCĐ] [nvarchar](255) NOT NULL,
    [TenCĐ] [nvarchar](255) NOT NULL,
	[NgayCham] [date] NOT NULL,
	[TrangThai] [bit] NOT NULL,
	
)
GO
/****** Object:  Table [dbo].[CaLamViec]    Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CaLamViec](
	[MaCa] [nvarchar](255) NOT NULL,
	[TenCa] [nvarchar](255) NOT NULL,
	[GioLam] [nvarchar](255) NOT NULL,
)

GO
/****** Object:  Table [dbo].[PhanCong]    Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhanCong](
	
	[MaCĐ] [nvarchar](255) NOT NULL,
	[MaCN] [nvarchar](255) NOT NULL,
	[TenCN] [nvarchar](255) NOT NULL,
	[TenCD] [nvarchar](255) NOT NULL,
	[MaSP] [nvarchar](255) NOT NULL,
	[TenSP][nvarchar](255) NOT NULL,
)

GO
/****** Object:  Table [dbo].[PhongBan]    Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhongBan](
	[MaPB] [nvarchar](255)  NOT NULL,
	[TenPB] [nvarchar](255) NOT NULL,
 ) 
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVienHanhChanh](
	[MaNV] [nvarchar](255) NOT NULL,
	[MaPB] [nvarchar](255) NOT NULL,
	[TenNV] [nvarchar](255) NOT NULL,
	[NgaySinh] [date] NOT NULL,
	[NgayThamGiaCT] [date] NOT NULL,
	[DiaChi] [nvarchar](255) NOT NULL,
	[Sđt] [nvarchar](255) NULL,
	[GioiTinh] [nvarchar](255) NOT NULL,
	[TrinhDo] [nvarchar](255) NOT NULL,
) 

GO
/****** Object:  Table [dbo].[LuongNhanVienHanhChanh]    Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[LuongNhanVienHanhChanh](
    [MaLuong] [nvarchar](255) NOT NULL,
	[MaPB] [nvarchar](255) NOT NULL,
	[MaPhieu] [nvarchar](255) NOT NULL,
	[LuongCoBan] [float] NOT NULL,
	[SoNgayTangCa] [int] NOT NULL,
	[SoNgayLamDuoc] [int] NOT NULL,
	[PhuCap] [int] NOT NULL,
	[ThangLuong] [int] NOT NULL,
	[NamLuong] [int] NOT NULL,
) 

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PhieuChamCong_NV]    Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PhieuChamCong_NV](
	[MaPhieu] [nvarchar](255) NOT NULL,
	[MaNV] [nvarchar](255) NOT NULL,
	[NgayCham] [date] NOT NULL,
	[DiLam] [bit] NOT NULL,
	[NghiPhep] [bit] NOT NULL,
	[TangCa] [bit] NOT NULL,
)
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[MaSP] [nvarchar](255) NOT NULL,
	[TenSP] [nvarchar](255) NOT NULL,
	[ThuongHieu] [nvarchar](255) NOT NULL,
	[DonGia] [float] NOT NULL,
	[SoLuong] [int] NOT NULL,
	[DonViTinh] [nvarchar](255) NOT NULL,
	[Anh] [nvarchar](255) NOT NULL
)

GO
/****** Object:  Table [dbo].[CongDoan]    Script Date: 23/9/2022 11:35:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CongDoan](
	[MaCĐ] [nvarchar](255)  NOT NULL,
	[MaSP] [nvarchar](255) NOT NULL,
	[TenSP] [nvarchar](255) NOT NULL,
	[TenCĐ] [nvarchar](255) NOT NULL,
	[DonGiaCĐ] [float] NOT NULL, 
    [SoLuong] [int] NOT NULL,
    [MaRangBuoc] int NOT NULL,
) 

GO


/* Tạo khóa chính */
ALTER TABLE [dbo].[LuongCongNhan]  ADD CONSTRAINT [FK_LuongCongNhan] PRIMARY KEY (MaLuong)
GO

ALTER TABLE [dbo].[CongNhan]  ADD CONSTRAINT [FK_CongNhan] PRIMARY KEY (MaCN)
GO

ALTER TABLE [dbo].[PhieuCong_CN]  ADD CONSTRAINT [FK_PhieuCong_CN] PRIMARY KEY (MaPhieu)
GO

ALTER TABLE [dbo].[CaLamViec]  ADD CONSTRAINT [FK_CaLamViec] PRIMARY KEY (MaCa)
GO



ALTER TABLE [dbo].[PhongBan]  ADD CONSTRAINT [FK_PhongBan] PRIMARY KEY (MaPB)
GO

ALTER TABLE [dbo].[NhanVienHanhChanh]  ADD CONSTRAINT [FK_NhanVienHanhChanh] PRIMARY KEY (MaNV)
GO

ALTER TABLE [dbo].[LuongNhanVienHanhChanh]  ADD CONSTRAINT [FK_LuongNhanVienHanhChanh] PRIMARY KEY (MaLuong)
GO

ALTER TABLE [dbo].[PhieuChamCong_NV]  ADD CONSTRAINT [FK_PhieuChamCong_NV] PRIMARY KEY (MaPhieu)
GO

ALTER TABLE [dbo].[SanPham]  ADD CONSTRAINT [FK_SanPham] PRIMARY KEY (MaSP)
GO

ALTER TABLE [dbo].[CongDoan]  ADD CONSTRAINT [FK_CongDoan] PRIMARY KEY (MaCĐ)
GO

/*Tạo khóa ngoại */

ALTER TABLE [dbo].[LuongCongNhan]  WITH CHECK ADD  CONSTRAINT [FK_LuongCongNhan_PhongBan] FOREIGN KEY([MaPB])
REFERENCES [dbo].[PhongBan] ([MaPB])
GO


GO

ALTER TABLE [dbo].[LuongCongNhan]  WITH CHECK ADD  CONSTRAINT [FK_LuongCongNhan_CaLamViec] FOREIGN KEY([MaPhanCong])
REFERENCES [dbo].[CaLamViec] ([MaCa])
GO

ALTER TABLE [dbo].[LuongCongNhan]  WITH CHECK ADD  CONSTRAINT [FK_LuongCongNhan_PhieuCong_CN] FOREIGN KEY([MaPhieu])
REFERENCES [dbo].[PhieuCong_CN] ([MaPhieu])
GO


GO

ALTER TABLE [dbo].[PhieuCong_CN]  WITH CHECK ADD  CONSTRAINT [FK_PhieuCong_CN_CaLamViec] FOREIGN KEY([MaCa])
REFERENCES [dbo].[CaLamViec] ([MaCa])
GO

ALTER TABLE [dbo].[PhanCong]  WITH CHECK ADD  CONSTRAINT [FK_PhanCong_CongDoan] FOREIGN KEY([MaCĐ])
REFERENCES [dbo].[CongDoan] ([MaCĐ])
GO

ALTER TABLE [dbo].[PhanCong]  WITH CHECK ADD  CONSTRAINT [FK_PhanCong_CongNhan] FOREIGN KEY([MaCN])
REFERENCES [dbo].[CongNhan] ([MaCN])
GO
ALTER TABLE [dbo].[PhanCong]  WITH CHECK ADD  CONSTRAINT [FK_PhanCong_SanPham] FOREIGN KEY([MaSP])
REFERENCES [dbo].[SanPham] ([MaSP])
GO

ALTER TABLE [dbo].[CongNhan]  WITH CHECK ADD  CONSTRAINT [FK_CongNhan_PhongBan] FOREIGN KEY([MaPB])
REFERENCES [dbo].[PhongBan] ([MaPB])
GO

ALTER TABLE [dbo].[NhanVienHanhChanh]  WITH CHECK ADD  CONSTRAINT [FK_NhanVienHanhChanh_PhongBan] FOREIGN KEY([MaPB])
REFERENCES [dbo].[PhongBan] ([MaPB])
GO

ALTER TABLE [dbo].[LuongNhanVienHanhChanh]  WITH CHECK ADD  CONSTRAINT [FK_LuongNhanVienHanhChanh_PhongBan] FOREIGN KEY([MaPB])
REFERENCES [dbo].[PhongBan] ([MaPB])
GO

ALTER TABLE [dbo].[LuongNhanVienHanhChanh]  WITH CHECK ADD  CONSTRAINT [FK_LuongNhanVienHanhChanh_PhieuChamCong_NV] FOREIGN KEY([MaPhieu])
REFERENCES [dbo].[PhieuChamCong_NV] ([MaPhieu])
GO

ALTER TABLE [dbo].[PhieuChamCong_NV]  WITH CHECK ADD  CONSTRAINT [FK_PhieuChamCong_NV_NhanVienHanhChanh] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVienHanhChanh] ([MaNV])
GO

ALTER TABLE [dbo].[CongDoan]  WITH CHECK ADD  CONSTRAINT [FK_CongDoan_SanPham] FOREIGN KEY([MaSP])
REFERENCES [dbo].[SanPham] ([MaSP])
GO 


INSERT [dbo].[PhongBan] ([MaPB], [TenPB]) VALUES (N'QL', N'Quản Lí')
INSERT [dbo].[PhongBan] ([MaPB], [TenPB]) VALUES (N'HC', N'Hành Chính')
INSERT [dbo].[PhongBan] ([MaPB], [TenPB]) VALUES (N'SX', N'Sản Xuất')
GO
INSERT [dbo].[Congnhan] ([MaCN], [MaPB], [TenCN], [Sđt], [DiaChi], [GioiTinh], [NgaySinh]) VALUES (N'CN01', N'SX', N'Nguyễn Trường Tuấn Kiệt',N'0786561865',N'Đồng Nai',N'Nam',  CAST(N'2002-10-27' AS Date))
INSERT [dbo].[Congnhan] ([MaCN], [MaPB], [TenCN], [Sđt], [DiaChi], [GioiTinh], [NgaySinh]) VALUES (N'CN02', N'HC', N'Nguyễn Anh Tuấn',N'0702345123',N'TP.HCM',N'Nam',  CAST(N'2000-11-17' AS Date))
INSERT [dbo].[Congnhan] ([MaCN], [MaPB], [TenCN], [Sđt], [DiaChi], [GioiTinh], [NgaySinh]) VALUES (N'CN03', N'QL', N'Nguyễn Văn Phú',N'0706789654',N'Vũng Tàu',N'Nữ',  CAST(N'2002-06-10' AS Date))
GO

INSERT [dbo].[NhanVienHanhChanh] ([MaNV], [MaPB], [TenNV], [NgaySinh], [NgayThamGiaCT], [DiaChi], [Sđt], [GioiTinh], [TrinhDo]) VALUES (N'NV01', N'HC', N'Phạm Thanh Hùng',CAST(N'1975-01-30' AS Date),CAST(N'2022-10-3' AS Date),N'Hà Nội',N'0908656767',N'Nam',N'Trung Cấp' )
INSERT [dbo].[NhanVienHanhChanh] ([MaNV], [MaPB], [TenNV], [NgaySinh], [NgayThamGiaCT], [DiaChi], [Sđt], [GioiTinh], [TrinhDo]) VALUES (N'NV02', N'QL', N'Phạm Thị Ngọc Hương',CAST(N'2002-05-24' AS Date),CAST(N'2022-10-2' AS Date),N'Tiền Giang',N'0207123456',N'Nữ',N'Cao Đẳng' )
INSERT [dbo].[NhanVienHanhChanh] ([MaNV], [MaPB], [TenNV], [NgaySinh], [NgayThamGiaCT], [DiaChi], [Sđt], [GioiTinh], [TrinhDo]) VALUES (N'NV03', N'SX', N'Nguyễn Thị Hoàng Khánh',CAST(N'1979-11-03' AS Date),CAST(N'2022-09-21' AS Date),N'Long An',N'0903412123',N'Nữ',N'Đại Học' )
GO

INSERT [dbo].[SanPham] ([MaSP], [TenSP], [ThuongHieu], [DonGia], [SoLuong], [DonViTinh], [Anh]) VALUES (N'SP01', N'PepSi Zero', N'PepsiCo',15000,250,N'Thùng', '\\src\image\\anhsp.jpg' )
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [ThuongHieu], [DonGia], [SoLuong], [DonViTinh],[Anh]) VALUES (N'SP02', N'Cocacola vị orginal', N'Coca-Cola',12000,200,N'Thùng','\\src\image\\anhsp2.jpg' )
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [ThuongHieu], [DonGia], [SoLuong], [DonViTinh],[Anh]) VALUES (N'SP03', N'Sting vị dâu', N'Coca-Cola',17000,300,N'Thùng','\\src\image\\anhsp3.jpg')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [ThuongHieu], [DonGia], [SoLuong], [DonViTinh],[Anh]) VALUES (N'SP04', N'Nước khoáng i-on Pocari Sweat', N'Warrior',9000,200,N'Chai','\\src\image\\anhsp4.jpg' )
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [ThuongHieu], [DonGia], [SoLuong], [DonViTinh],[Anh]) VALUES (N'SP05', N'Nước tăng lực Warrior hương nho', N'Coca-Cola',10000,300,N'Chai','\\src\image\\anhsp5.jpg')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [ThuongHieu], [DonGia], [SoLuong], [DonViTinh],[Anh]) VALUES (N'SP06', N'Nước tinh khiết Aquafina', N'Aquafina',5000,30,N'Thùng','\\src\image\\anhsp6.jpg')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [ThuongHieu], [DonGia], [SoLuong], [DonViTinh],[Anh]) VALUES (N'SP07', N'Nước tinh khiết Dasani', N'Danasi',17000,300,N'Thùng','\\src\image\\anhsp7.jpg')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [ThuongHieu], [DonGia], [SoLuong], [DonViTinh],[Anh]) VALUES (N'SP08', N'Sữa trái cây Nutriboost hương cam', N'Nutriboost',35000,20,N'Thùng','\\src\image\\anhsp8.jpg')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [ThuongHieu], [DonGia], [SoLuong], [DonViTinh],[Anh]) VALUES (N'SP09', N'Nước sữa trái cây TH True Milk cam', N'TH True Juice Milk',17000,300,N'Thùng','\\src\image\\anhsp9.jpg')
INSERT [dbo].[SanPham] ([MaSP], [TenSP], [ThuongHieu], [DonGia], [SoLuong], [DonViTinh],[Anh]) VALUES (N'SP10', N'Nước ngọt Fanta hương xá xị', N'Fanta',17000,300,N'Thùng','\\src\image\\anhsp10.jpg')
GO
go


/*INSERT [dbo].[CongDoan] ([MaCĐ], [MaSP], [TenCĐ], [DonGiaCĐ], [SoLuong], [MaRangBuoc],[TrangThai]) VALUES (N'CĐ01', N'', N'Coca-Cola',17000,300,N'Thùng' )
GO*/

--insert [dbo].[CongDoan]([MaCĐ],[MaSP],[TenCĐ],[DonGiaCĐ],[SoLuong],[MaRangBuoc],[TrangThai]) values (N'CD01', N'SP01',N'Công Đoạn 1', 12000, 25, 1,1);
--insert [dbo].[CongDoan]([MaCĐ],[MaSP],[TenCĐ],[DonGiaCĐ],[SoLuong],[MaRangBuoc],[TrangThai]) values (N'CD02', N'SP02',N'Công Đoạn 1', 15000, 35, 2,0);
--insert [dbo].[CongDoan]([MaCĐ],[MaSP],[TenCĐ],[DonGiaCĐ],[SoLuong],[MaRangBuoc],[TrangThai]) values (N'CD03', N'SP03',N'Công Đoạn 2', 15000, 35, 3,1);
--insert [dbo].[CongDoan]([MaCĐ],[MaSP],[TenCĐ],[DonGiaCĐ],[SoLuong],[MaRangBuoc],[TrangThai]) values (N'CD04', N'SP04',N'Công Đoạn 3', 35000, 45, 4,0);
--insert [dbo].[CongDoan]([MaCĐ],[MaSP],[TenCĐ],[DonGiaCĐ],[SoLuong],[MaRangBuoc],[TrangThai]) values (N'CD05', N'SP05',N'Công Đoạn 4', 15000, 15, 5,1);
--insert [dbo].[CongDoan]([MaCĐ],[MaSP],[TenCĐ],[DonGiaCĐ],[SoLuong],[MaRangBuoc],[TrangThai]) values (N'CD06', N'SP06',N'Công Đoạn 1', 25000, 25, 6,0);
--insert [dbo].[CongDoan]([MaCĐ],[MaSP],[TenCĐ],[DonGiaCĐ],[SoLuong],[MaRangBuoc],[TrangThai]) values (N'CD07', N'SP07',N'Công Đoạn 2', 15000, 55, 7,0);
--insert [dbo].[CongDoan]([MaCĐ],[MaSP],[TenCĐ],[DonGiaCĐ],[SoLuong],[MaRangBuoc],[TrangThai]) values (N'CD08', N'SP08',N'Công Đoạn 3', 35000, 25, 8,1);
--insert [dbo].[CongDoan]([MaCĐ],[MaSP],[TenCĐ],[DonGiaCĐ],[SoLuong],[MaRangBuoc],[TrangThai]) values (N'CD09', N'SP09',N'Công Đoạn 4', 25000, 15, 9,0);
--insert [dbo].[CongDoan]([MaCĐ],[MaSP],[TenCĐ],[DonGiaCĐ],[SoLuong],[MaRangBuoc],[TrangThai]) values (N'CD10', N'SP10',N'Công Đoạn 1', 15000, 15, 10,1);
USE [master]
GO
ALTER DATABASE [QuanLyLuongSanPham] SET  READ_WRITE 
GO

INSERT [dbo].[CaLamViec] ([MaCa], [TenCa],[GioLam]) VALUES (N'CA01', N'Sáng',N'7h-11h')
INSERT [dbo].[CaLamViec] ([MaCa], [TenCa],[GioLam]) VALUES (N'CA02', N'Chiều',N'13h-17h')
INSERT [dbo].[CaLamViec] ([MaCa], [TenCa],[GioLam]) VALUES (N'CA03', N'Tối',N'18h-23h')

SELECT * FROM SanPham

select CONCAT('SP', RIGHT(CONCAT('00',ISNULL(right(max(maSP),2),0) + 1),2)) from SanPham where maSP like  'SP%'

 --DROP DATABASE [QuanLyLuongSanPham]
 