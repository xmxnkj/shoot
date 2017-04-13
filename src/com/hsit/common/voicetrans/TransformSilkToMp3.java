package com.hsit.common.voicetrans;

import java.io.IOException;

import org.nutz.lang.Encoding;
import org.nutz.lang.Lang;

public class TransformSilkToMp3 {
	
	public static void main(String[] args){

        String skil = "E:\\1a5e5f36334f41eb8992f4f27debd1a6.silk";
       String pcm = "E:\\1.pcm";
        String mp3 = "E:\\1.mp3";

        boolean b = getPcm(skil,pcm);
        System.out.println(b);
        if (b)
          getMp3(pcm,mp3);

    }

    /**
     * 解码为pcm格式
     * @param silk 源silk文件,需要绝对路径!! 例:F:\zhuanma\vg2ub41omgipvrmur1fnssd3tq.silk
     * @param pcm 目标pcm文件,需要绝对路径!! 例:F:\zhuanma\vg2ub41omgipvrmur1fnssd3tq.pcm
     * @return
     */
    public static boolean getPcm(String silk,String pcm){
        boolean flag = true;
        String cmd="cmd.exe /c D:\\silk-v3-decoder-master\\windows\\silk_v3_decoder.exe "+silk+" "+pcm+" -quiet";
        System.out.println("转码到pcm...");
        try
        {
            StringBuilder msg = Lang.execOutput(cmd, Encoding.CHARSET_GBK);
            System.out.println(msg);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 转码为MP3格式
     * @param pcm 源pcm文件,需要绝对路径!!  例:F:\zhuanma\vg2ub41omgipvrmur1fnssd3tq.pcm
     * @param mp3 目标mp3文件,需要绝对路径!! 例:F:\zhuanma\vg2ub41omgipvrmur1fnssd3tq.mp3
     * @return
     */
    public static boolean getMp3(String pcm,String mp3){
        boolean flag = true;
        System.out.println("转码到mp3...");
        try {
            StringBuilder sb = Lang.execOutput("cmd /c D:\\silk-v3-decoder-master\\ffmpeg.exe -y -f s16le -ar 24000 -ac 1 -i "+pcm+" "+mp3+"", Encoding.CHARSET_GBK);
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

}
