<?php include_once('db.php');?>
<html>
<head>
<title>

</title>
</head>
</html>
<body>
<!--date from-->
    <?php 
        echo 'Date From: ';
        //month
        for($i=0;$i<=11;$i++){
            $month=date('M',strtotime("first day of $i month"));
            echo $month ."<br>";
            }
            //year
        echo "<select name=year>";
        for($i=0;$i<=5;$i++){
        $year=date('Y',strtotime("last day of +$i year"));
        echo "<option name='$year'>$year</option>";
        }
        echo "</select>";
    ?>



    <?php //echo 'Date To: ';
   // $result = retrive("select * from te");
    //TODO: Logic for table 
    ?>


 
    <?php echo 'Reason: ';?>


</body>