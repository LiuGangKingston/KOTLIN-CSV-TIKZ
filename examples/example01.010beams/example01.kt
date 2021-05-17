/*!   This is example01 source file for
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
!   "Specific calculation to generate CSV files" area in the
!   functin "void MyComputing() {...}" at the end of the
!   template file.
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
    val startingline=1;
    val refractiveindex=1.5e0;
    val bigradius=8.0e0;
    val a=3.0e0;
    val b=3.0e0;
    val z=Math.sqrt(bigradius * bigradius- b * b);
    val anglez=Math.asin(b/bigradius)*csts.Rad2Deg;
    val c=-Math.sqrt(bigradius * bigradius - (a+b) * (a+b));
    val anglea=Math.acos(c/bigradius)*csts.Rad2Deg;

    try {
         val afile = File("setup.scalars.csv");
         afile.writeText("totallines,refractiveindex,bigradius,a,b,z,anglez,c,anglea\n");
         afile.appendText(totallines.toString() + "," + refractiveindex.toString() + ","
               + bigradius.toString() + "," + a.toString() + "," + b.toString() + ","
               + z.toString() + "," + anglez.toString() + "," + c.toString() + ","
               + anglea.toString() + "\n");
    } catch(e: Exception) {
         println("An error occurred when working on the file setup.scalars.csv, then stopped.");
         e.printStackTrace()
         exitProcess(4);
    }

    try {
         val bigfile = File("iterated.alldata.csv");
         bigfile.writeText("totallines,i,refractiveindex,bigradius,a,b,z,anglez,c,"+
                             "anglea,incidentangle,refractiveangle,anglede,dx,ee,et,ex,ey,"+
                             "anglece,angleced,outangle,mycolor"+"\n");
         for (i in startingline..totallines) {
              var incidentangle=3.0e0 + 05.0e0*i;
              var refractiveangle=Math.asin(Math.sin(incidentangle*csts.Deg2Rad)/refractiveindex)*csts.Rad2Deg;
              var anglede=180-refractiveangle-anglea;
              // x component of D position
              var dx=a/Math.tan(anglede*csts.Deg2Rad) + c;

              //! To find E position by solving equations, with t and et as DE length:
              //!  (t sin(anglede) + \b)^2 + (t cos(anglede) + \dx )^2 = 64
              //!  t^2 + 2 (\b sin + \dx cos ) + (\b sin + \dx cos )^2
              //!  = 64 - \b^2 - \dx^2 + (\b sin +  \dx cos  )^2
              //!  t = sqrt ((\b sin + \dx cos )^2 +  64 - \b^2 - \dx^2 )  - (\b sin + \dx cos )

              var ee=b*Math.sin(anglede*csts.Deg2Rad) + dx*Math.cos(anglede*csts.Deg2Rad);
              var et=Math.sqrt(ee * ee + bigradius * bigradius -b * b - dx * dx) - ee;

              //! x and y components of E position
              var ex=et*Math.cos(anglede*csts.Deg2Rad) + dx;
              var ey=et*Math.sin(anglede*csts.Deg2Rad) + b;
              var anglece=Math.acos(ex/Math.sqrt(ex * ex+ey * ey))*csts.Rad2Deg;
              var angleced=anglece-anglede;
              var outangle=Math.asin(Math.sin(angleced*csts.Deg2Rad) * refractiveindex)*csts.Rad2Deg;

              bigfile.appendText(totallines.toString()+ "," + i+ ","
                  + refractiveindex.toString()+ "," + bigradius.toString() + ","+ a.toString()
                  + "," + b.toString() + "," + z.toString()+ "," + anglez.toString()+ ","
                  + c.toString() + "," + anglea.toString() + "," + incidentangle.toString()
                  + "," + refractiveangle.toString() + "," + anglede.toString() + ","
                  + dx.toString() + "," + ee.toString() + "," + et.toString() + ","
                  + ex.toString() + "," + ey.toString() + "," + anglece.toString() + ","
                  + angleced.toString() + "," + outangle.toString() + ","
                  + csts.PickTypicalColor(i) + "\n");
         }
    } catch(e: Exception) {
         println("An error occurred when working on the file the BIG FILEs, then stopped.");
         e.printStackTrace()
         exitProcess(4);
    }
}



