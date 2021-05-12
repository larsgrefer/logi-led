package de.larsgrefer.logi.jna.cv;

import com.sun.jna.Memory;
import com.sun.jna.platform.win32.*;

public class ScreenCapture {

    private WinDef.HDC hScreenDC;
    private WinDef.HDC hdcMemDC;

    private int width;
    private int height;

    private WinGDI.BITMAPINFO bmi;

    private Memory buffer;

    public ScreenCapture() {
        //this.hScreenDC = WinGDI.;
        this.hdcMemDC = GDI32.INSTANCE.CreateCompatibleDC(hScreenDC);

        this.width = GDI32.INSTANCE.GetDeviceCaps(hScreenDC, 0x8);
        this.height = GDI32.INSTANCE.GetDeviceCaps(hScreenDC, 0xa);

        this.bmi = new WinGDI.BITMAPINFO();
        bmi.bmiHeader.biWidth = width;
        bmi.bmiHeader.biHeight = -height;
        bmi.bmiHeader.biPlanes = 1;
        bmi.bmiHeader.biBitCount = 32;
        bmi.bmiHeader.biCompression = WinGDI.BI_RGB;

        this.buffer = new Memory(width * height * 4);
    }

    public Memory capture() {
        WinDef.HBITMAP hBitmap = GDI32.INSTANCE.CreateCompatibleBitmap(hScreenDC, width, height);
        WinNT.HANDLE hOld = GDI32.INSTANCE.SelectObject(hdcMemDC, hBitmap);
        GDI32.INSTANCE.BitBlt(hdcMemDC, 0, 0, width, height, hScreenDC, 0, 0, WinGDIExtra.SRCCOPY);
        GDI32.INSTANCE.SelectObject(hdcMemDC, hOld);
        GDI32.INSTANCE.GetDIBits(hScreenDC, hBitmap, 0, height, buffer, bmi, WinGDI.DIB_RGB_COLORS);
        GDI32.INSTANCE.DeleteObject(hBitmap);
        return this.buffer;
    }
}

