//   This is template source file for
//       https://github.com/LiuGangKingston/KOTLIT-CSV-TIKZ.git
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
//   You can code your specific computations in the area of
//   "Specific calculation to generate CSV files" at the
//   end of this file.
//

import java.io.File
import java.io.FileWriter;
import java.io.IOException;
import kotlin.system.exitProcess

fun main() {

    val csts = ConstantsForKotlinCSVTikZ();

//  Specific calculation to generate CSV files
//  Specific calculation to generate CSV files
//  like the following lines:
  
    val totallines=500;
    val startingline=1;
    val datalinesineachfile=50;
    ...

    // The next object creation will also open files "iterated.alldata.1.csv", 
    //                                               "iterated.alldata.2.csv", 
    //                                               "iterated.alldata.3.csv", 
    //                                               ..., 
    //                                               till the last needed one:
    val bigfiles = KotlinCSVTikZFileGroup("iterated.alldata.",startingline,totallines,datalinesineachfile);

    // The next routine call will output the long string into all the above files as the top line:
    bigfiles.FirstLineToFiles("variablenames"+"seperate"+"bycommaswithoutanythingelse\n");

    for (i in startingline..totallines) {
        ...

        // The next statement will output data of variables into the specific file based on "i" value. 
        (bigfiles.GetFileForRow(i)).appendText(    onevariable.toString() + "," + anothervariable.toString() + ","  
          + anothervariable.toString() + "," + anothervariable.toString() + "," + anothervariable.toString() + "," 
          + anothervariable.toString() + "," + anothervariable.toString() + "," + anothervariable.toString() + "," 
          + anothervariable.toString() + "," + anothervariable.toString() + "," + anothervariable.toString() + "," 
          + anothervariable.toString() + "," + anothervariable.toString() + "," + anothervariable.toString() + "," 
          + anothervariable.toString() + "," + anothervariable.toString() + "," + bigfile.PickTikZColor(i) + "\n");
    }

}



