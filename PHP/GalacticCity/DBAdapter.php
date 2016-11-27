<?php

/**
 * Created by Oclemy for ProgrammingWizards and http://www.camposha.com.
 * User: Oclemy
 * Date: 9/16/2016
 * Time: 12:54 AM
 */
require_once("/Constants.php");

class DBAdapter
{
/*******************************************************************************************************************************************/
/*
   1.CONNECT TO DATABASE.
   2. RETURN CONNECTION OBJECT
*/
    public function connect()
    {
       

       $con=mysqli_connect(Constants::$DB_SERVER,Constants::$USERNAME,Constants::$PASSWORD,Constants::$DB_NAME);

        if(mysqli_connect_error(!$con))
        {
           // echo "Unable To Connect";
            return null;
        }else
        {
           
            return $con;
        }
    }
	/*******************************************************************************************************************************************/
/*
   1.INSERT SPACECRAFT INTO DATABASE
 */
    public function insert($s)
    {
        // INSERT
        $con=$this->connect();
        
        if($con != null)
        {
            $sql="INSERT INTO galileoTB(name,propellant,destination) VALUES('$s[0]','$s[1]','$s[2]')";

            try
            {
                $result=mysqli_query($con,$sql);

                if($result)
                {
                    print(json_encode(array("Success")));
                }else
                {
                    print(json_encode(array("Unsuccessfull")));
                }
            }catch (Exception $e)
            {
               print(json_encode(array("PHP EXCEPTION : CAN'T SAVE TO MYSQL. "+$e->getMessage())));
            }


        }else{
            print(json_encode(array("PHP EXCEPTION : CAN'T CONNECT TO MYSQL. NULL CONNECTION.")));
        }

        mysqli_close($con);
    }
/*******************************************************************************************************************************************/
/*
   1.SELECT FROM DATABASE.
*/
    public function select()
    {
       $con=$this->connect();

        if($con != null)
        {
            $retrieved=mysqli_query($con,Constants::$SQL_SELECT_ALL);
            if($retrieved)
            {
                while($row=mysqli_fetch_array($retrieved))
                {
                   
                   // echo $row["name"] ,"    \t | ",$row["propellant"],"</br>";
				   $spacecrafts[]=$row;
                }
				print(json_encode($spacecrafts));
            }else
            {
                 print(json_encode(array("PHP EXCEPTION : CAN'T RETRIEVE FROM MYSQL. ")));
            }
        }else{
            print(json_encode(array("PHP EXCEPTION : CAN'T CONNECT TO MYSQL. NULL CONNECTION.")));
        }

        mysqli_close($con);
    }
/*******************************************************************************************************************************************/
/*
   1.UPDATE  DATABASE.
  
*/
    public function update($id, $s)
    {
        // UPDATE

        $con=$this->connect();
      
        if($con != null)
        {

            $sql="UPDATE galileoTB SET name='$s[0]',propellant='$s[1]',destination='$s[2]' WHERE id='$id'";


            try
            {
                $result=mysqli_query($con,$sql);


                if($result)
                {
                    print(json_encode(array("Successfully Updated")));
                }else
                {
                    print(json_encode(array("Not Successfully Updated")));
                }
            }catch (Exception $e)
            {
                 print(json_encode(array("PHP EXCEPTION : CAN'T UPDATE INTO MYSQL. "+$e->getMessage())));
            }


        }else{
            print(json_encode(array("PHP EXCEPTION : CAN'T CONNECT TO MYSQL. NULL CONNECTION.")));
        }

        mysqli_close($con);
    }
/*******************************************************************************************************************************************/
/*
   1.DELETE SPACECRAFT FROM DATABASE.
  
*/
    public function delete($id)
    {
        $con=$this->connect();
       
        if($con != null)
        {

//            $name=$_POST['Name'];
//            $pos=$_POST['Position'];
//            $team=$_POST['Team'];

            $sql="DELETE FROM galileoTB WHERE id='$id'";


            try
            {
                $result=mysqli_query($con,$sql);


                if($result)
                {
                    print(json_encode(array("Successfully Deleted")));
                }else
                {
                    print(json_encode(array("Not Successfully Deleted")));
                }
            }catch (Exception $e)
            {
                print(json_encode(array("PHP EXCEPTION : CAN'T DELETE FROM MYSQL. "+$e->getMessage())));
            }


        }else{
            print(json_encode(array("PHP EXCEPTION : CAN'T CONNECT TO MYSQL. NULL CONNECTION.")));
        }

        mysqli_close($con);
    }

   
}
