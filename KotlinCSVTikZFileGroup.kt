//   This is the supporting source file for
//       https://github.com/LiuGangKingston/KOTLIN-CSV-TIKZ.git
//            Version 1.0
//   free for non-commercial use.
//   Please send us emails for any problems/suggestions/comments.
//   Please be advised that none of us accept any responsibility
//   for any consequences arising out of the usage of this
//   software, especially for damage.
//   For usage, please refer to the README file.
//   This code was written by
//        Gang Liu (gl.cell@outlook)
//                 (http://orcid.org/0000-0003-1575-9290)
//          and
//        Shiwei Huang (huang937@gmail.com)
//   Copyright (c) 2021
//
//
//
//import java.util.Arrays;
//import java.util.ArrayList;
//import java.io.IOException;
import java.io.File
import java.io.FileWriter;
import kotlin.system.exitProcess

public class KotlinCSVTikZFileGroup(
    val FileNamePrefix: String,
    val StartingRow:    Int,
    val EndingRow:      Int,
    val RowsInEachFile: Int
){
    private val KotlinCSVTikZFileExtension : String = ".csv"
    private val FileNamePrefixes = mutableListOf<String>()
    private val TheFilesOpened = mutableListOf<File>()
    private var TotalFileGroups : Int = 0
    private var StartingRowNumber : Int = 0
    private var EndingRowNumber : Int = 0
    private var TotalRowsInEachFile : Int = 0
    private var AbsoluteRowRange : Int = 0
    private var RowNumberDirection : Int = 0
    private var TotalFiles : Int = 0
    private var i: Int = 0
    private var j: Int = 0
    private var k: Int = 0
    private var l: Int = 0;

    init {
        if(FileNamePrefix.isEmpty()) {
           println("Sorry for empty FileNamePrefix: "+FileNamePrefix+" \n");
           println("stopped, when creating a KotlinCSVTikZFileGroup object.\n");
           exitProcess(4);
        }

        if(RowsInEachFile < 1) {
           println("Sorry for empty FileNamePrefix: "+FileNamePrefix+" \n");
           println("stopped, when creating a KotlinCSVTikZFileGroup object.\n");
           exitProcess(4);
        }

        TotalRowsInEachFile = RowsInEachFile;
        StartingRowNumber = StartingRow;
        EndingRowNumber = EndingRow;
        AbsoluteRowRange = Math.abs(EndingRow - StartingRow);
        RowNumberDirection = 1;
        if (StartingRow > EndingRowNumber) {
            RowNumberDirection = -1; }
        TotalFiles = Math.abs(StartingRow - EndingRow) / RowsInEachFile + 1;

        for (i in 1..(TotalFiles)) {
               TheFilesOpened.add(File(FileNamePrefix+i.toString()+KotlinCSVTikZFileExtension));
/*            var filename = FileNamePrefix+i.toString()+KotlinCSVTikZFileExtension;
            var anewfile = File(filename)
            var fileExists = anewfile.exists()
            if(fileExists){
               TheFilesOpened.add(File(FileNamePrefix+i.toString()+KotlinCSVTikZFileExtension));
               println("The file "+filename+" is opened as No. "+i.toString()+" \n");
            } else {
               println("The file "+filename+" open is failed, \n");
               println("stopped, when creating a KotlinCSVTikZFileGroup object.\n");
              // exitProcess(4);
            } */
        }

        FileNamePrefixes.add(FileNamePrefix);
        TotalFileGroups++;
    }


    //fun FileGroupClose(): Unit {
    //    for(i in 0..(TotalFiles-1)) {
    //        (TheFilesOpened.get(i)).close();
    //    }
    //}


    fun FirstLineToFiles(FirstLine: String ): Unit {
        for(i in 0..(TotalFiles-1)) {
            (TheFilesOpened.get(i)).writeText(FirstLine+"\n")
        }
    }


    fun GetFileForRow(RowNumber: Int): File{
        k = RowNumber-StartingRowNumber;
        if ((k*RowNumberDirection) < 0) {
            println("The RowNumber should be from "+StartingRowNumber+" to "+EndingRowNumber+" \n");
            println("Stopped for bad RowNumber "+RowNumber+" in GetFileForRow() of class KotlinCSVTikZFileGroup.\n");
            exitProcess(4);
        }
        if (Math.abs(k) > AbsoluteRowRange){
            println("The RowNumber should be from "+StartingRowNumber+" to "+EndingRowNumber+" \n");
            println("Stopped for bad RowNumber "+RowNumber+" in GetFileForRow() of class KotlinCSVTikZFileGroup.\n");
            exitProcess(4);
       }
        j = Math.abs(StartingRowNumber - RowNumber) / TotalRowsInEachFile;
        return (TheFilesOpened.get(j));
    }

}

// The end of class KotlinCSVTikZFileGroup and file.
// The end of class KotlinCSVTikZFileGroup and file.
// The end of class KotlinCSVTikZFileGroup and file.




fun main() {

    val refractiveindex=1.5e0;
    val bigradius=8.0e0;
    val a=3.0e0;
    val b=3.0e0;
    val totallines=340;
    //val ll = List(3, 4.5, "fh")

    val numbers = mutableListOf(1, 2, 3, 4)
    numbers.add(5)
    println(numbers)
    val nt = 6
    val nteight = nt * 12

    val gf = KotlinCSVTikZFileGroup("ktf.", 0, nteight,7)

gf.FirstLineToFiles("fghtr,ere,trre,tyt");

    for (i in 0..nteight) {
         gf.GetFileForRow(i).appendText(i.toString()+", "+refractiveindex.toString()+",".toString()+b.toString()+"\n")
    }




}

