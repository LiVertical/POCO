$(function() {
    // ������ŵ�ǰ���ڲ����������ı�������á�
    var datepicker_CurrentInput;
    $.datepicker.regional['zh-CN'] = {
        closeText : '�ر�',
        prevText : '����',
        prevStatus : '��ʾ����',
        prevBigText : '<<',
        prevBigStatus : '��ʾ��һ��',
        nextText : '����',
        nextStatus : '��ʾ����',
        nextBigText : '>>',
        nextBigStatus : '��ʾ��һ��',
        currentText : '���',
        currentStatus : '��ʾ����',
        monthNames : [ 'һ��', '����', '����', '����', '����', '����', '����', '����', '����',
                'ʮ��', 'ʮһ��', 'ʮ����' ],
        monthNamesShort : [ '1', '2', '3', '4', '5', '6', '7', '8', '9', '10',
                '11', '12' ],
        monthStatus : 'ѡ���·�',
        yearStatus : 'ѡ�����',
        weekHeader : '��',
        weekStatus : '�����ܴ�',
        dayNames : [ '������', '����һ', '���ڶ�', '������', '������', '������', '������' ],
        dayNamesShort : [ '����', '��һ', '�ܶ�', '����', '����', '����', '����' ],
        dayNamesMin : [ '��', 'һ', '��', '��', '��', '��', '��' ],
        dayStatus : '���� DD Ϊһ����ʼ',
        dateStatus : 'ѡ�� m�� d��, DD',
        dateFormat : 'yy-mm-dd',
        firstDay : 1,
        maxDate: +(-1),
        
        gotoCurrent : true,
        initStatus : '��ѡ������',
        isRTL : false,
        showMonthAfterYear : true, // ������֮����ʾ
		//changeMonth : true, // ����ѡ���·�
        //changeYear : true, // ����ѡ�����
        showOn : 'focus', // ��������Ա���ʾ��ť������Ĭ��Ϊ��focus������������Ϊboth ,button
        //showOtherMonths : true,
        showButtonPanel : true,
       // showWeek: true,
		showAnim:'show',//��ʾ����show��slideDown��fadeIn(Ĭ��)
        beforeShow : function(input, inst) {
            datepicker_CurrentInput = input;
        }
    };
    // �󶨡�Today����ť��click�¼���������ʱ������ı����ֵ
   $(".ui-datepicker-current").live("click", function() {
		datepicker_CurrentInput.value = '';
		$(".ui-datepicker-close").click();
		$(datepicker_CurrentInput).blur();
		
	});
	//�������ڿ����ʽ
	//var style=$("<style type='text/css' src='./red-datepicker.css'></style>");
	//$("head").append(style);
	$.datepicker.setDefaults($.datepicker.regional['zh-CN']);
});

	  