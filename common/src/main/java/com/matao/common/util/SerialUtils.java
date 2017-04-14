package com.matao.common.util;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.UUID;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by matao on 2016-12-11 16:45
 */

public class SerialUtils {

    private SerialUtils() {
        throw new UnsupportedOperationException("SerialUtils cannot be instantiated!");
    }

    /**
     * GSF Service (Google Service Framework)
     */
    private static final Uri GSF_URI = Uri.parse("content://com.google.android.gsf.gservices");

    /**
     * Key del ID
     */
    private static final String GSF_ID_KEY = "android_id";

    /**
     * Devuelve el Device ID segun GSF (Google Service Framework)<br>
     * Ej: 3189147f8714e99a<br>
     * Se necesita: < uses-permission android:name=
     * "com.google.android.providers.gsf.permission.READ_GSERVICES" /> en el
     * AndroidManisfest.xml
     *
     * @param context Contexto de Aplicacion o Actividad
     * @return cadena con el id
     */
    public static String getGSFId(Context context) {
        final String[] params = {GSF_ID_KEY};
        final Cursor c = context.getContentResolver().query(GSF_URI, null, null, params, null);

        if (!c.moveToFirst() || c.getColumnCount() < 2)
            return null;

        try {
            return Long.toHexString(Long.parseLong(c.getString(1)));
        } catch (NumberFormatException e) {
            return null;
        } finally {
            if (c != null)
                c.close();
        }
    }

    @SuppressLint("NewApi")
    public static String getBuildSerialId() {
        String deviceId;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD) {
            deviceId = Build.SERIAL;
        } else {
            deviceId = "undefined";
        }
        return deviceId;
    }

    public static String getAndroidId(Context context) {
        String deviceId = null;
        deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (deviceId == null || deviceId.equalsIgnoreCase("android_id")
                || deviceId.equalsIgnoreCase("9774d56d682e549c")) {
            deviceId = "undefined";
        }
        return deviceId;
    }

    public static String getImei(Context context) {
        String tmDevice = null;
        try {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
            tmDevice = tm.getDeviceId();
            if (tmDevice == null) {
                tmDevice = "undefined";
            }
        } catch (Exception ex) {
            tmDevice = "undefined";
        }

        return tmDevice;
    }

    public static String getImsi(Context context) {
        String tmDevice;
        try {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
            tmDevice = tm.getSubscriberId();
            if (tmDevice == null) {
                tmDevice = "undefined";
            }
        } catch (Exception ex) {
            tmDevice = "undefined";
        }

        return tmDevice;
    }

    private static String deviceId = null;

    /**
     * Devuelve un Identificador unico de DISPOSITO Android UUID en base a un
     * algoritmo propio que utiliza el DeviceID(IMEI nulo si no es telefono),
     * Serial SIM(nulo si no hay SIM) y ANDROID_ID<br>
     * Si cambia cualquiera de estos parametros, cambiará el Identificador
     * Calculado.<br>
     * Ej: 00000000-381a-6648-50b0-ec5a0033c587<br>
     * En el peor de los casos, devuelve el android.os.Build.SERIAL o
     * Settings.Secure.ANDROID_ID<br>
     *
     * @return String con el Device ID calculado
     */
    public static String getUUId(Context context) {

        if (!TextUtils.isEmpty(deviceId))
            return deviceId;

        try {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);

            final String tmDevice, tmSerial, androidId;
            tmDevice = "" + tm.getDeviceId();
            tmSerial = "";
            androidId = ""
                    + Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);

            UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
            deviceId = deviceUuid.toString();
        } catch (Exception e) {
            // Intentamos Tomar el SERIAL del Hardware -> porque el Terminal era
            // un TELEFONO pero mal configurado (sin SIM o error con datos
            // incorrectos en la Telefonia)
            try {
                deviceId = getSerialDeviceId(context);
            } catch (Exception e1) {
                throw new RuntimeException("FATAL!!!! - This device doesn't have a UNIQUE Serial Number", e);
            }
        }
        return deviceId;
    }

    private static String uniqueId = null;

    public static String getUniqueId(Context context) {

        if (!TextUtils.isEmpty(uniqueId))
            return uniqueId;

        try {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);

            final String tmDevice, serial;
            tmDevice = "" + tm.getDeviceId();
            serial = getSerialNumber();

            UUID deviceUuid = new UUID(serial.hashCode(), (long) tmDevice.hashCode());
            uniqueId = deviceUuid.toString();
        } catch (Exception e) {
            // Intentamos Tomar el SERIAL del Hardware -> porque el Terminal era

            // un TELEFONO pero mal configurado (sin SIM o error con datos

            // incorrectos en la Telefonia)

            try {
                uniqueId = getSerialDeviceId(context);
            } catch (Exception e1) {
                throw new RuntimeException("FATAL!!!! - This device doesn't have a UNIQUE Serial Number", e);
            }
        }

        return uniqueId;
    }

    public static String getSerialNumber() {
        return Build.SERIAL + "";
    }

    /**
     * Devuelve una cadena de Descripcion del Dispositivo:<br>
     * Build.MANUFACTURER + Build.MODEL + Build.VERSION.RELEASE<br>
     * Ej: MID Tagus Tablet - 4.0.3
     *
     * @return
     */
    public static String getPid() {
        StringBuilder pid = new StringBuilder(Build.MANUFACTURER).append(" ").append(Build.MODEL).append(" - ")
                .append(Build.VERSION.RELEASE);
        return pid.toString();
    }

    /**
     * Devuelve el SERIAL del Hardware (android.os.Build.SERIAL), o el SERIAL de
     * Arranque del Dispositivo (Settings.Secure.ANDROID_ID)<br>
     * Ej: 202ec37cf8d93ece
     *
     * @return un numero de Serie unico del Hardware
     * @throws Exception Si el algoritmo de calculo falla
     */
    public static String getSerialDeviceId(Context context) throws Exception {
        // Intentamos Tomar el SERIAL del Hardware
        String deviceId = null;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD) {
            deviceId = Build.SERIAL; // Android 2.3 y superiores
        }
        if (deviceId == null || deviceId.equalsIgnoreCase("unknown")) {
            // tomamos el ANDROID_ID => si esto es NULO, no podemos IDENTIFICAR
            // al Aparato de forma UNICA
            deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            if (deviceId == null || deviceId.equalsIgnoreCase("android_id")
                    || deviceId.equalsIgnoreCase("9774d56d682e549c")) {
                throw new Exception("FATAL!!!! - This device doesn't have a UNIQUE Serial Number");
            }
        }
        return deviceId;
    }

    /**
     * Devuelve el SERIAL del Hardware "Tagus Tablet" (IMEI)<br>
     * Ej: 202141237121581
     *
     * @return un numero de Serie unico del Hardware Tagus (IMEI)
     * @throws Exception Si el algoritmo de calculo falla
     */
    public static String getDeviceId(Context context) {
        String tmDevice = null;
        try {
            // Intentamos Tomar el SERIAL del Hardware de Telefonia
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
            tmDevice = tm.getDeviceId(); // the IMEI for GSM
            if (tmDevice == null) {
                // Si es un TABLET "Tagus" pero NO tiene Telefonia
                tmDevice = "unknown";
            }
        } catch (Exception ex) {
            tmDevice = "unknown";
        }

        return tmDevice;
    }

    public static String getMacAddress() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter != null) {
            return adapter.getAddress();
        } else {
            return "none";
        }
    }

    /**
     * 生成设备唯一标识：IMEI、AndroidId、macAddress 三者拼接再 MD5
     * @return
     */
    public static String generateUniqueDeviceId(Context context){
        String imei = "";
        String androidId = "";
        String macAddress = "";

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            imei = telephonyManager.getDeviceId();
        }
        ContentResolver contentResolver = context.getContentResolver();
        if (contentResolver != null) {
            androidId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID);
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            macAddress = wifiManager.getConnectionInfo().getMacAddress();
        }

        StringBuilder longIdBuilder = new StringBuilder();
        if (imei != null) {
            longIdBuilder.append(imei);
        }
        if (androidId != null) {
            longIdBuilder.append(androidId);
        }
        if (macAddress != null) {
            longIdBuilder.append(macAddress);
        }
        return MD5Utils.getMD5(longIdBuilder.toString());
    }
}
