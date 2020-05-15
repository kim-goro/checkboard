<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<link rel="stylesheet" type="text/css" href="/css/common.css" />
</head>
<body>
	<div class="flexContainer flexCenter" style="flex-direction: column;">
		<form class="solidForm" action="/checkboard/regmod.do" method="POST">
		
			<!-- checkboardVO -->
			<div class="marBottom10">
				제목:
				<input type="text" name="checkboard_title" placeholder="제목" value="title">
			</div>
			<div class="marBottom10">
				설명:
				<textarea name="checkboard_decription" value="description"></textarea>
			</div>
			<div class="marBottom10">
				듀데이:
				<input type="date" name="due_dt" value="2020-05-15">
				</div>
			
			<!-- checkboardGoalVO -->
			<div id="pre_set" style="display:none;" last-id="0">
  				목표:  <input type="text" name="goalName">
	         	<span class="delete_box">
	            	<button type="button" onclick="delete_info(this)" style="font-size:70%">삭제</button>
	            </span>
        	</div>

			<div id="field" style="max-height:770px; overflow:auto;">
			</div>

			<button type="button" onclick="add_info()">추가</button>
			
			<!-- checkboardParticipantsVO -->
			<div class="marBottom10">
				<input type="submit" value="생성하기">
			</div>
			
		</form>
	</div>
<script>
 function add_info() {
    var pre_set = document.getElementById('pre_set');
    var fieldid = pre_set.getAttribute('last-id');
    pre_set.setAttribute('last-id', fieldid + 1 );

    var div = document.createElement('div');
    div.innerHTML = pre_set.innerHTML;
    div.id = 'field-data-' + fieldid;
    var deleteBox = div.getElementsByClassName('delete_box')[0];
    deleteBox.setAttribute('target',div.id);
    document.getElementById('field').appendChild(div);
}

function delete_info(obj) {
   var target = obj.parentNode.getAttribute('target');
   var field = document.getElementById(target);
    document.getElementById('field').removeChild(field);
}

</script>
</body>
</html>