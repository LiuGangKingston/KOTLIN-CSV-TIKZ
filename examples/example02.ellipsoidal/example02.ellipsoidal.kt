/*!   This is example02.ellipsoidal source file for
!       https://github.com/LiuGangKingston/KOTLIN-CSV-TIKZ.git
!            Version 1.0
!   free for non-commercial use.
!   Please send us emails for any problems/suggestions/comments.
!   Please be advised that none of us accept any responsibility
!   for any consequences arising out of the usage of this
!   software, especially for damage.
!   For usage, please refer to the README file.
!   This code was written by
!        Gang Liu (gl.cell@outlook)
!                 (http://orcid.org/0000-0003-1575-9290)
!          and
!        Shiwei Huang (huang937@gmail.com)
!   Copyright (c) 2021
!
!   This file is formed by adding new lines at the
!   "Specific calculation to generate CSV files" area at the
!   end of the template file.
! */

import java.io.File
import java.io.FileWriter;
import java.io.IOException;
import kotlin.system.exitProcess

fun main() {

    val csts = ConstantsForKotlinCSVTikZ();

//  Specific calculation to generate CSV files
//  Specific calculation to generate CSV files

    val totallines=10;
    val datalinesineachfile=50;
    val startingline=1;

    val a=7.0e0;
    val b=3.0e0;
    val startingangleofsoidal = 35.0e0;
    val endinggangleofsoidal = 325.0e0;
    val startinxofsoidal = a * Math.cos(startingangleofsoidal*csts.Deg2Rad);
    val startinyofsoidal = b * Math.sin(startingangleofsoidal*csts.Deg2Rad);
    val c=-Math.sqrt(a*a-b*b);

    try {
         val afile = File("setup.scalars.csv");
         afile.writeText("a,b,c,startingangleofsoidal,endinggangleofsoidal,startinxofsoidal,startinyofsoidal\n");
         afile.appendText(a.toString() + "," + b.toString() + "," + c.toString() + ","
                      + startingangleofsoidal.toString() + "," + endinggangleofsoidal.toString()
                      + "," + startinxofsoidal.toString() + "," + startinyofsoidal.toString() +"\n");
    } catch(e: Exception) {
         println("An error occurred when working on the file setup.scalars.csv, then stopped.");
         e.printStackTrace()
         exitProcess(4);
    }

    val bigfile = KotlinCSVTikZFileGroup("iterated.alldata.",startingline,totallines,datalinesineachfile);
    bigfile.FirstLineToFiles("c,d,startingangle,dk,bigf,t,x,y,yprime," +
                          "tangentangle,normalangle,incidentangle,reflectangle,mycolor"+"\n");

    val d=Math.abs(c);
    for (j in -1..2 step 2) {
    for (i in startingline..totallines) {
       var startingangle= j*(5.0e0 + 5.0e0*i*i);
       var dk=d*Math.cos(startingangle*csts.Deg2Rad);
       var bigf=a*a - dk*dk;
       var t=b*b*(dk+Math.sqrt(bigf+dk*dk))/bigf;
       var x=t*Math.cos(startingangle*csts.Deg2Rad)-d;
       var y=t*Math.sin(startingangle*csts.Deg2Rad);
       var yprime=-b*b*x/(a*a*y);
       var z = Math.sqrt((b*b*x)*(b*b*x) + (a*a*y)*(a*a*y));
       var tangentangle: Double = 0.0e0;
       if(x < 0.0e0) {
          if(y < 0.0e0) {
             tangentangle = Math.asin(b*b*x /z) * csts.Rad2Deg; }
          else {
             tangentangle = 180.0e0 + Math.acos(a*a*y /z) * csts.Rad2Deg; }
       }
       else {
          if(y  < 0.0e0) {
             tangentangle = Math.acos(-a*a*y /z) * csts.Rad2Deg;}
          else {
             tangentangle = Math.acos(-a*a*y /z) * csts.Rad2Deg;}
       }
       var normalangle = tangentangle - 90.0e0;
       var incidentangle = normalangle - startingangle;
       var reflectangle = tangentangle + 90.0e0 + incidentangle;

       try {
            (bigfile.GetFileForRow(i)).appendText(c.toString() + "," + d.toString() + ","
                     + startingangle.toString() + "," + dk.toString() + "," + bigf.toString() + ","
                     + t.toString() + "," + x.toString() + "," + y.toString() + ","
                     + yprime.toString() + "," + tangentangle.toString() + "," + normalangle.toString()
                     + "," + incidentangle.toString() + "," + reflectangle.toString() + ","
                     + csts.PickTypicalColor(i) + "\n");
       } catch(e: Exception) {
         println("An error occurred when working on the files iterated.alldata.*.csv, then stopped.");
         e.printStackTrace()
         exitProcess(4);
       }
    }
    }

}

