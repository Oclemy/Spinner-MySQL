<?php

/**
 * Created by Oclemy for ProgrammingWizards and http://www.camposha.com.
 * User: Oclemy
 * Date: 9/16/2016
 * Time: 12:54 AM
 */
require_once("/DBAdapter.php");

if($_POST['action']=="save"){
        
   		 $dbAdapter=new DBAdapter();
         $name=$_POST['name'];
         $propellant=$_POST['propellant'];
         $destination=$_POST['destination'];

        $dbAdapter->insert(array($name,$propellant,$destination));
}else if($_POST['action']=="update")
{
	     $dbAdapter=new DBAdapter();
		 $id=$_POST['id'];
         $name=$_POST['name'];
         $propellant=$_POST['propellant'];
         $destination=$_POST['destination'];

        $dbAdapter->update($id,array($name,$propellant,$destination));
	
}else if($_POST['action']=="delete")
{
	     $dbAdapter=new DBAdapter();
         $id=$_POST['id'];
        
        $dbAdapter->delete($id);
}
?>