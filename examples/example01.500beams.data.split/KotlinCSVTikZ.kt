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

public class ConstantsForKotlinCSVTikZ{
    val PI             : Double = 3.14159265358979323846e0;
    val Rad2Deg        : Double = 57.29577951308232e0     ;  // 180/pi;
    val Deg2Rad        : Double = 0.017453292519943296e0  ;  // pi/180;
    val NapierConstant : Double = 2.71828182845904523536e0;
    val EulerNumber    : Double = 2.71828182845904523536e0;

    val AccelerationDueToEarthGravity  : Double = 9.80e0               ;// "m/s$^2$"
    val AtomicMassConstant             : Double = 1.66053906660e-27    ;// kg
    val AvogadroNumber                 : Double = 6.02214076e23        ;// "mol$^{-1}$"
    val BohrMagneton                   : Double = 9.2740100783e-24     ;// "J/T"
    val BohrRadius                     : Double = 5.29177210903e-11    ;// m
    val BoltzmannConstant              : Double = 1.380649e-23         ;// "J/K"
    val ClassicalElectronRadius        : Double = 2.8179403262e-15     ;// m
    val CoulombConstant                : Double = 8.9875517923e9       ;// "N$\cdot $m$^2$/C$^2$"
    val ElementaryCharge               : Double = 1.602176634e-19      ;// "C"
    val FaradayConstant                : Double = 9.648533212e4        ;// C/mol
    val FineStructureConstant          : Double = 7.2973525693e-3      ;//
    val FirstRadiationConstant         : Double = 3.741771852e-16      ;// W$\dot m^2$
    val MassOfElectron                 : Double = 9.1093837015e-31     ;// "kg"
    val MassOfNeutron                  : Double = 1.67492749804e-27    ;// "kg"
    val MassOfProton                   : Double = 1.67262192369e-27    ;// "kg"
    val NuclearMagneton                : Double =  5.0507837461e-27    ;// "J/T"
    val PlanckConstant                 : Double = 6.62607015e-34       ;// "J$\cdot $s"
    val RydbergConstant                : Double = 1.0973731568160e7    ;// 1/m
    val SecondRadiationConstant        : Double = 1.438776877e-2       ;// m$\dot K$
    val SpeedoFlightInVacuum           : Double = 2.99792458e+8        ;// "m/s"
    val ThomsonCrossSection            : Double = 6.6524587321e-29     ;//  $m^2$
    val UniversalGasConstant           : Double = 8.314462618e0        ;// "J/(mol$\cdot $K)"
    val UniversalGravitationalConstant : Double = 6.67430e-11          ;// "N$\cdot $m$^2$/kg$^2$"
    val VacuumElectricPermittivity     : Double = 8.8541878128e-12     ;// "F/m"
    val VacuumMagneticPermeability     : Double = 1.25663706212e-6     ;// "N/$A^2$"

    val TypicalColors = arrayOf<String>(
                         "red","orange","yellow","green","blue");

    val TikZColors = arrayOf<String>(
                         "red","purple","magenta","pink",
                         "violet","white","orange","yellow",
                         "green","lime","brown","olive",
                         "blue","cyan","teal","lightgray",
                         "gray","darkgray","black"           );

    val Colors = arrayOf<String>(
                         "Apricot","Aquamarine","Bittersweet","Black",
                         "Blue","BlueGreen","BlueViolet","BrickRed",
                         "Brown","BurntOrange","CadetBlue","CarnationPink",
                         "Cerulean","CornflowerBlue","Cyan","Dandelion",
                         "DarkOrchid","Emerald","ForestGreen","Fuchsia",
                         "Goldenrod","Gray","Green","GreenYellow",
                         "JungleGreen","Lavender","LimeGreen","Magenta",
                         "Mahogany","Maroon","Melon","MidnightBlue",
                         "Mulberry","NavyBlue","OliveGreen","Orange",
                         "OrangeRed","Orchid","Peach","Periwinkle",
                         "PineGreen","Plum","ProcessBlue","Purple",
                         "RawSienna","Red","RedOrange","RedViolet",
                         "Rhodamine","RoyalBlue","RoyalPurple","RubineRed",
                         "Salmon","SeaGreen","Sepia","SkyBlue",
                         "SpringGreen","Tan","TealBlue","Thistle",
                         "Turquoise","Violet","VioletRed","White",
                         "WildStrawberry","Yellow","YellowGreen","YellowOrange");

    init {}

    fun PickTypicalColor(i: Int): String {
           return TypicalColors[Math.abs(i)%(TypicalColors.size)];
    }

    fun PickTikZColor(i: Int): String {
           return TikZColors[Math.abs(i)%(TikZColors.size)];
    }

    fun PickColor(i: Int): String {
           return Colors[Math.abs(i)%(Colors.size)];
    }
}




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
           println("Sorry for empty FileNamePrefix: "+FileNamePrefix);
           println("stopped, when creating a KotlinCSVTikZFileGroup object.");
           exitProcess(4);
        }

        if(RowsInEachFile < 1) {
           println("Sorry for empty FileNamePrefix: "+FileNamePrefix);
           println("stopped, when creating a KotlinCSVTikZFileGroup object.");
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
            var filename = FileNamePrefix+i.toString()+KotlinCSVTikZFileExtension;
            try{
                TheFilesOpened.add(File(filename));
                println("The file "+filename+" is opened as No. "+i.toString());
            } catch(e: Exception) {
                println("The file "+filename+" open is failed, ");
                println("stopped, when creating a KotlinCSVTikZFileGroup object.");
		        e.printStackTrace()
                exitProcess(4);
            }
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
            println("The RowNumber should be from "+StartingRowNumber+" to "+EndingRowNumber);
            println("Stopped for bad RowNumber "+RowNumber+" in GetFileForRow() of class KotlinCSVTikZFileGroup.");
            exitProcess(4);
        }
        if (Math.abs(k) > AbsoluteRowRange){
            println("The RowNumber should be from "+StartingRowNumber+" to "+EndingRowNumber);
            println("Stopped for bad RowNumber "+RowNumber+" in GetFileForRow() of class KotlinCSVTikZFileGroup.");
            exitProcess(4);
       }
        j = Math.abs(StartingRowNumber - RowNumber) / TotalRowsInEachFile;
        return (TheFilesOpened.get(j));
    }

}

// The end of KotlinCSVTikZ classes and file.
// The end of KotlinCSVTikZ classes and file.

