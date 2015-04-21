<%@tag pageEncoding="UTF-8"%>
<script type="text/html" id='pagebar_template'>
	<$var paginationSize = 5;$>
	<div class="pagination">
	<ul>
		<$if(firstPage){$>
			<li class="disabled"><a href="#"> &lt;&lt;</a> </li>
       		<li class="disabled"><a href="#"> &lt; </a></li>
		<$}else{$>
			<li  ><a href="#" onclick="pageNav(0)">&lt;&lt; </a></li>
			<li  ><a href="#" onclick="pageNav(<$=number-1$>)"> &lt;</a></li>
		<$}$>
		<$var current =  number + 1;$>
		<$var begin = Math.max(1, current - Math.floor(paginationSize/2));$>
		<$var end = Math.min(begin + paginationSize-1, totalPages);$>
		<$if(end-begin<paginationSize-1 && totalPages>=paginationSize){$>
			<$begin = totalPages-paginationSize+1;$>
			<$end = totalPages;$>
		<$}$>
		<$for(var i=begin;i<=end;i++){$>
			<$if(i==current){$>
				<li class="active"> <a href="#"> <$=i$></a> </li>	
			<$}else{$>
				<li  ><a href="#" onclick="pageNav(<$=i-1$>)">  <$=i$> </a></li>	
			<$}$>
		<$}$>
		<$if(lastPage){$>
			<li class="disabled"> <a href="#"> &gt; </a></li>
			<li class="disabled"> <a href="#"> &gt;&gt; </a></li>
		<$}else{$>
			<li  > <a href="#" onclick="pageNav(<$=number+1$>)"> &gt; </a></li>
			<li  > <a href="#" onclick="pageNav(<$=totalPages-1$>)"> &gt;&gt; </a></li>		
		<$}$>
	</ul>
</div>
</script>

