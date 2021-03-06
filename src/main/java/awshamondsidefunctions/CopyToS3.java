/*
 * Copyright 2016 YU Jia

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package awshamondsidefunctions;

import java.io.IOException;
import org.apache.hadoop.fs.Path;
import sharedsidefunctions.HadoopUser;

/**
 *
 * @author yujia1986
 */
public class CopyToS3 {

    public static void copyToS3(String outPut) throws IOException, InterruptedException {

        String userName = HadoopUser.getHadoopUser();
        //get output file name
        String outputName = new Path(outPut).getName();
        Path outputPath = new Path(outPut).getParent();

        //mapreduce single output file back to s3 using user specified output file name
        String copyCommand[] = {"hadoop", "distcp", "/user/"+userName+"/Hamond/"+outputName, outputPath.toString()};
        Process p = Runtime.getRuntime().exec(copyCommand);
        p.waitFor();
        
    }

}
