const { log , dir } = console;

window.addEventListener("load" , ()=>{
	
	tinyMCE.init({
		selector : "#content",
		plugins : "advlist autolink link image lists charmap print preview codesample emoticons textcolor",
		
		toolbar : ['undo redo | styleselect | bold italic | link imageupload codesample | forecolor backcolor emoticons'],
		height : 400,
		menubar : false,
		codesample_languages : [
			{ text : "HTML/XML" , value: 'markup' },
			{ text : "Javscript" , value: 'javascript' },
			{ text : "CSS" , value: 'css' },
			{ text : "Java" , value: 'java' },
		],
		
		content_style: "body { line-height : 0.2; padding : 0.25rem; }",
		setup : function( editor ){
			// setup => tiny 실행 -> 에디터 객체 반환 -> 환경설정 수행 ㄱㄴ
			
			let inp = $(`<input type='file' name="pic" id="tinymce-uploader" accept="image/*" style="display : none;">`)
			$(editor.getElement()).parent().append(inp);
			editor.addButton( "imageupload" , {
				icon : 'image',
				onclick : function(e){
					inp.click();
				}
			});
			
			inp.on("change", (e)=>{
				let input = inp[0]; // 제이쿼리 객체의 0번쨰는 원본 DOM 
				let data = new FormData();
				data.append("file" , input.files[0]);
				
				$.ajax({
					url : "/board/upload",
					type : "POST",
					data : data,
					enctype : 'multipart/form-data',
					dataType : 'json',
					processData : false,
					// processData 를 true 로 놓게 되면 강제로 쿼리스트링이 된다.
					contentType : false,
					// 전송 데이터 타입을 false 로 주면 제이쿼리가 알아서 판단한다.
					success : ( data )=>{
						editor.insertContent(`<img class="content-img" src="${ data.uploadImage }" data-mce-src="${ data.uploadImage }">`);

					},
					error : ( error )=>{
						log(error);
					}
				});
				
			});
		}
	});
	
});