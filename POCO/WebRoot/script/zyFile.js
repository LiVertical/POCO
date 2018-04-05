var ZYFILE = {
		fileInput : null,             // ѡ���ļ���ťdom����
		uploadInput : null,           // �ϴ��ļ���ťdom����
		dragDrop: null,				  //��ק��������
		url : "",  					  // �ϴ�action·��
		uploadFile : [],  			  // ��Ҫ�ϴ����ļ�����
		lastUploadFile : [],          // ��һ��ѡ����ļ����飬��������ϴ�ʹ��
		perUploadFile : [],           // ������õ��ļ����飬����ɾ��ʹ��
		fileNum : 0,                  // �����ļ��ܸ�������Ϊ�漰��������ӣ�������һ�������Ҫ�����Ļ������������
		/* �ṩ���ⲿ�Ľӿ� */
		filterFile : function(files){ // �ṩ���ⲿ�Ĺ����ļ���ʽ�ȵĽӿڣ��ⲿ��Ҫ�ѹ��˺���ļ�����
			return files;
		},
		onSelect : function(selectFile, files){      // �ṩ���ⲿ��ȡѡ�е��ļ������ⲿʵ��Ԥ���ȹ���  selectFile:��ǰѡ�е��ļ�  allFiles:��û�ϴ���ȫ���ļ�
			
		},
		onDelete : function(file, files){            // �ṩ���ⲿ��ȡɾ���ĵ����ļ������ⲿʵ��ɾ��Ч��  file:��ǰɾ�����ļ�  files:ɾ��֮����ļ�
			
		},
		onProgress : function(file, loaded, total){  // �ṩ���ⲿ��ȡ�����ļ����ϴ����ȣ����ⲿʵ���ϴ�����Ч��
			
		},
		onSuccess : function(file, responseInfo){    // �ṩ���ⲿ��ȡ�����ļ��ϴ��ɹ������ⲿʵ�ֳɹ�Ч��
			
		},
		onFailure : function(file, responseInfo){    // �ṩ���ⲿ��ȡ�����ļ��ϴ�ʧ�ܣ����ⲿʵ��ʧ��Ч��
		
		},
		onComplete : function(responseInfo){         // �ṩ���ⲿ��ȡȫ���ļ��ϴ���ɣ����ⲿʵ�����Ч��
			
		},
		
		/* �ڲ�ʵ�ֹ��ܷ��� */
		// ���ѡ�е��ļ�
		//�ļ��Ϸ�
		funDragHover: function(e) {
			e.stopPropagation();
			e.preventDefault();
			this[e.type === "dragover"? "onDragOver": "onDragLeave"].call(e.target);
			return this;
		},
		// ��ȡ�ļ�
		funGetFiles : function(e){  
			var self = this;
			// ȡ����꾭����ʽ
			this.funDragHover(e);
			// ���¼��л�ȡѡ�е������ļ�
			var files = e.target.files || e.dataTransfer.files;
			self.lastUploadFile = this.uploadFile;
			this.uploadFile = this.uploadFile.concat(this.filterFile(files));
			var tmpFiles = [];
			
			// ��Ϊjquery��inArray�����޷���object��������ж��Ƿ�����ڣ�����ֻ����ȡ���ƽ����ж�
			var lArr = [];  // ֮ǰ�ļ�����������
			var uArr = [];  // �����ļ�����������
			$.each(self.lastUploadFile, function(k, v){
				lArr.push(v.name);
			});
			$.each(self.uploadFile, function(k, v){
				uArr.push(v.name);
			});
			
			$.each(uArr, function(k, v){
				// ��õ�ǰѡ���ÿһ���ļ�   �жϵ�ǰ��һ���ļ��Ƿ������֮ǰ���ļ�����
				if($.inArray(v, lArr) < 0){  // ������
					tmpFiles.push(self.uploadFile[k]);
				}
			});
			
			// ���tmpFiles���й�������һ��ѡ����ļ��Ĳ�������Ҫ�ѹ��˺���ļ���ֵ
			//if(tmpFiles.length!=0){
				this.uploadFile = tmpFiles;
			//}
			
			// ���ö��ļ�����ķ���
			this.funDealtFiles();
			
			return true;
		},
		// ������˺���ļ�����ÿ���ļ������±�
		funDealtFiles : function(){
			var self = this;
			// Ŀǰ�Ǳ������е��ļ�����ÿ���ļ�����Ψһ����ֵ
			$.each(this.uploadFile, function(k, v){
				// ��Ϊ�漰��������ӣ�������һ�������Ҫ���ܸ����Ļ��������
				v.index = self.fileNum;
				// ���һ��֮������
				self.fileNum++;
			});
			// �Ȱѵ�ǰѡ�е��ļ����汸��
			var selectFile = this.uploadFile;  
			// Ҫ��ȫ�����ļ���������������Ϊɾ����ʹ�õ��±���ȫ�ֵı���
			this.perUploadFile = this.perUploadFile.concat(this.uploadFile);
			// �ϲ����ϴ����ļ�
			this.uploadFile = this.lastUploadFile.concat(this.uploadFile);
			
			// ִ��ѡ��ص�
			this.onSelect(selectFile, this.uploadFile);
			console.info("����ѡ��");
			console.info(this.uploadFile);
			return this;
		},
		// ������Ҫɾ�����ļ�  isCb�����Ƿ�ص�onDelete����  
		// ��Ϊ�ϴ���ɲ���ϣ����ҳ����ɾ��div�����ǵ������ɾ����ʱ����Ҫɾ��div   ������isCb���ж�
		funDeleteFile : function(delFileIndex, isCb){
			var self = this;  // ��each��thisָ��û��v  �����Ƚ�this����
			
			var tmpFile = [];  // �����滻���ļ�����
			// �ϲ����ϴ����ļ�
			var delFile = this.perUploadFile[delFileIndex];
			console.info(delFile);
			// Ŀǰ�Ǳ������е��ļ����Ա�ÿ���ļ�  ɾ��
			$.each(this.uploadFile, function(k, v){
				if(delFile != v){
					// �������ɾ�����Ǹ��ļ� �ͷŵ���ʱ������
					tmpFile.push(v);
				}else{
					
				}
			});
			this.uploadFile = tmpFile;
			if(isCb){  // ִ�лص�
				// �ص�ɾ�����������ⲿ����ɾ��Ч����ʵ��
				self.onDelete(delFile, this.uploadFile);
			}
			
			console.info("��ʣ��Щ�ļ�û���ϴ�:");
			console.info(this.uploadFile);
			return true;
		},
		// �ϴ�����ļ�
		funUploadFiles : function(){
			var self = this;  // ��each��thisָ��û��v  �����Ƚ�this����
			// ���������ļ�  ���ڵ��õ����ļ��ϴ��ķ���
			$.each(this.uploadFile, function(k, v){
				self.funUploadFile(v);
			});
		},
		// �ϴ��������ļ�
		funUploadFile : function(file){
			var self = this;  // ��each��thisָ��û��v  �����Ƚ�this����
			
			var formdata = new FormData();
			formdata.append("fileList", file);	         		
			var xhr = new XMLHttpRequest();
			// ���ϴ��¼�
			// ����
		    xhr.upload.addEventListener("progress",	 function(e){
		    	// �ص����ⲿ
		    	self.onProgress(file, e.loaded, e.total);
		    }, false); 
		    // ���
		    xhr.addEventListener("load", function(e){
	    		// ���ļ���ɾ���ϴ��ɹ����ļ�  false�ǲ�ִ��onDelete�ص�����
		    	self.funDeleteFile(file.index, false);
		    	// �ص����ⲿ
		    	self.onSuccess(file, xhr.responseText);
		    	if(self.uploadFile.length==0){
		    		// �ص�ȫ����ɷ���
		    		self.onComplete("ȫ�����");
		    	}
		    }, false);  
		    // ����
		    xhr.addEventListener("error", function(e){
		    	// �ص����ⲿ
		    	self.onFailure(file, xhr.responseText);
		    }, false);  
			
			xhr.open("POST",self.url, true);
			xhr.setRequestHeader("X_FILENAME", file.name);
			xhr.send(formdata);
		},
		// ������Ҫ�ϴ����ļ�
		funReturnNeedFiles : function(){
			return this.uploadFile;
		},
		
		// ��ʼ��
		init : function(){  // ��ʼ���������ڴ˸�ѡ���ϴ���ť���¼�
			var self = this;  // ��¡һ������
			
			if (this.dragDrop) {
				this.dragDrop.addEventListener("dragover", function(e) { self.funDragHover(e); }, false);
				this.dragDrop.addEventListener("dragleave", function(e) { self.funDragHover(e); }, false);
				this.dragDrop.addEventListener("drop", function(e) { self.funGetFiles(e); }, false);
			}
			
			// ���ѡ��ť����
			if(self.fileInput){
				// ��change�¼�
				this.fileInput.addEventListener("change", function(e) {
					self.funGetFiles(e); 
				}, false);	
			}
			
			// ����ϴ���ť����
			if(self.uploadInput){
				// ��click�¼�
				this.uploadInput.addEventListener("click", function(e) {
					self.funUploadFiles(e); 
				}, false);	
			}
		}
};














