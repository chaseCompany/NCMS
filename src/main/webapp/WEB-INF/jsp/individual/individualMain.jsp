<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javaScript" language="javascript" defer="defer">
	$(document).ready(function(){
		<%-- 초기화 --%>
		individualNew = function(){
			window.location.reload();
		},
		individualExel = function(){
			alert('준비중 입니다.');
			console.log("엑셀다운로드");
		},
		<%-- 회원 조회 --%>
		mstMbrSearchPopup = function(resFuct){
			$.ajax({
				url : '/ajaxMstMbrList.do',
				type : 'POST',
				data : {
					pageNo : $("input[name='memPageNo']").val(),
					mbrNm : $("input[name='memSchMbrNm']").val(),
					telNo : $("input[name='memSchTelNo']").val()
				},
				success : function(res){
					$("div[id='layerpopup']").html(res);
					$("div[id='layerpopup']").attr("data-popup", "memberPopUp");
					$("input[name='reFunName']").val("setMemInfo");
					layerPopupOpen('memberPopUp');
				},
				error : function(xhr, status){
					console.log(xhr);
				}
			});
		},
		<%-- 회원정보 셋팅 --%>
		setMemInfo = function(obj){
			$("input[name='mbrNo']").val(obj.MBR_NO);
			$("input[name='mbrNm']").val(obj.MBR_NM);
			$("input[name='gendNm']").val(obj.GEND_NM);
			$("input[name='age']").val(obj.AGE);
			$("input[name='regDt']").val(formatDate(obj.REG_DT));
			$("input[name='medicCareNm']").val(obj.MEDIC_CARE_NM);
			$("input[name='mngUsrId']").val(obj.MNG_USR_NM);

			getCslIdvList(obj.MBR_NO);
			getCslIspList(obj.MBR_NO);
			getCslAssList(obj.MBR_NO);

			$("button#excelNo").hide();
			$("button#excelYes").show();
			$("button#idvSaveBtnNo").hide();
			$("button#idvSaveBtnYes").show();
			$("button#ispSaveButNo").hide();
			$("button#ispSaveButYes").show();
			$("button#assSaveButNo").hide();
			$("button#assSaveButYes").show();
		},
		<%-- 집중상담 이력 조회 --%>
		getCslIdvList = function(tagMbrNo){
			$.ajax({
				url : '/getClsIdvList.do',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo
				},
				success : function(res){
					if(res.err != "Y"){
						$("div#idvList").html("");

						if(res.clsIdvList.length > 0){
							var inHtml = "<table id='idvTableList'>"
									   + "	<colgroup>"
									   + "		<col style='width:46px'>"
									   + "		<col style='width:150px'>"
									   + "		<col style='width:100px'>"
									   + "		<col style='width:120px'>"
									   + "		<col style='width:80px'>"
									   + "		<col style='width:400px'>"
									   + "		<col style='width:690px'>"
									   + "		<col style='width:150px'>"
									   + "		<col>"
									   + "	</colgroup>"
									   + "	<tbody>";

							$(res.clsIdvList).each(function(idx, obj){
								inHtml = inHtml
									   + "<tr id=" + idx + ">"
									   + "	<td><div class='cell'>" + (idx + 1) + "</div></td>"
									   + "	<td><a href='javaScript:viewIdvRow(\"" + obj.CSL_NO + "\");' class='row_link'>" + obj.CSL_NO + "</a></td>"
									   + "	<td><div class='cell'>" + formatDate(obj.CSL_DT) + "</div></td>"
									   + "	<td><div class='cell'>" + obj.CSL_FM_TM + " ~ " + obj.CSL_TO_TM + "</div></td>"
									   + "	<td><div class='cell'>" + obj.CSL_TERM_TM + "</div></td>"
									   + "	<td><div class='cell'>" + obj.CSL_SBJ + "</div></td>"
									   + "	<td><div class='cell'>" + obj.CSL_TGT + "</div></td>"
									   + "	<td><div class='cell'>" + obj.CSL_ID + "</div></td>"
									   + "	<td><div class='cell'>"
									   + "		<button type='button' onclick='javaScript:idvDel(\"" + obj.CSL_NO + "\", \"" + idx + "\");' class='el-button el-button--danger el-button--mini is-plain' style='margin-left: 1px; padding: 4px 9px;'> <span>삭제</span> </button>"
									   + "	</div></td>"
									   + "</tr>";
							});

							inHtml = inHtml
								   + "	</tbody>"
								   + "</table>";

							$("div#idvList").html(inHtml);
						}else{
							$("div#idvList").html("<div class='no-data'>조회된 데이터가 없습니다.</div>");
						}
					}else{
						console.log("ERR")
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- 집중상담내용 삭제 --%>
		idvDel = function(tagCslNo, idx){
			$.ajax({
				url : '/ajaxClsIdvDel.do',
				type : 'POST',
				data : {
					cslNo : tagCslNo
				},
				success : function(res){
					if(res.err != "Y"){
						$("#idvTableList tr").each(function(){
							if($(this).attr("id") == idx){
								$(this).remove();
							}
						});

						if($("#idvTableList tr").length <= 0){
							$("div#idvList").html("<div class='no-data'>조회된 데이터가 없습니다.</div>");
						}

						newIdv();
					}else{
						console.log(res.MSG);
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- 집중상담내용 상세 조회 --%>
		viewIdvRow = function(tagCslNo){
			$.ajax({
				url : '/ajaxClsIdvInfo.do',
				type : 'POST',
				data : {
					cslNo : tagCslNo
				},
				success : function(res){
					if(res.err != "Y"){
						$("input[name='cslNo']").val(res.clsIdvInfo.CSL_NO);
						$("input[name='cslNm']").val(res.clsIdvInfo.CSL_NM);
						$("input[name='cslDt']").val(formatDate(res.clsIdvInfo.CSL_DT));
						$("input[name='cslFmTm']").val(res.clsIdvInfo.CSL_FM_TM);
						$("input[name='cslToTm']").val(res.clsIdvInfo.CSL_TO_TM);
						$("input[name='cslTermTm']").val(res.clsIdvInfo.CSL_TERM_TM);
						$("input[name='cslTgtCd']:radio[value='" + res.clsIdvInfo.CSL_TGT_CD + "']").prop("checked", true);
						$("input[name='cslTpCd']:radio[value='" + res.clsIdvInfo.CSL_TP_CD + "']").prop("checked", true);
						$("input[name='cslSbj']").val(res.clsIdvInfo.CSL_SBJ);
						$("input[name='cslTgt']").val(res.clsIdvInfo.CSL_TGT);
						$("textarea[name='cslCtnt']").val(res.clsIdvInfo.CSL_CTNT);
						$("input[name='rskSco']").val(res.clsIdvInfo.RSK_SCO);
						$("select[name='rskaTpCd']").val(res.clsIdvInfo.RSKA_TP_CD).prop("selected", true);
						$("select[name='rskbTpCd']").val(res.clsIdvInfo.RSKB_TP_CD).prop("selected", true);
						$("select[name='rskcTpCd']").val(res.clsIdvInfo.RSKC_TP_CD).prop("selected", true);
						$("input[name='nxtCslDt']").val(formatDate(res.clsIdvInfo.NXT_CSL_DT));
						$("input[name='nxtCslTm']").val(res.clsIdvInfo.NXT_CSL_TM);
						$("textarea[name='nxtCslCtnt']").val(res.clsIdvInfo.NXT_CSL_CTNT);

						var termTm = needTime($("input[name='cslFmTm']").val(), $("input[name='cslToTm']").val());
						if(termTm > 0){
							$("span#cslTermTm").text(termTm);
							$("input[name='cslTermTm']").val(termTm);
						}

						changRating();
					}else{
						console.log("ERR")
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- 집중상담 신규 --%>
		newIdv = function(){
			$("input[name='cslNo']").val("");
			$("input[name='cslDt']").val("");
			$("input[name='cslFmTm']").val("");
			$("input[name='cslToTm']").val("");
			$("span#cslTermTm").text("0");
			$("input[name='cslTgtCd']:radio[value='10']").prop("checked", true);
			$("input[name='cslTpCd']:radio[value='20']").prop("checked", true);
			$("input[name='cslSbj']").val("");
			$("input[name='cslTgt']").val("");
			$("textarea[name='cslCtnt']").val("");
			$("select[name='rskaTpCd']").val("0").prop("selected", true);
			$("select[name='rskbTpCd']").val("0").prop("selected", true);
			$("select[name='rskcTpCd']").val("0").prop("selected", true);
			$("span#ratingNum").text("0");
			$("input[name='nxtCslDt']").val("");
			$("input[name='nxtCslTm']").val("");
			$("textarea[name='nxtCslCtnt']").val("");
		},
		<%-- 집중상담 저장 --%>
		saveIdv = function(){
			if($("input[name='mbrNo']").val() == ""){
				alert("회원을 선택하세요.");
				return;
			}
			if($("input[name='cslDt']").val() == ""){
				alert("집중 상담일는 필수 입력 항목입니다.");
				$("input[name='cslDt']").focus();					return;
			}
			if($("input[name='cslFmTm']").val() == ""){
				alert("집중 상담 시간은 필수 입력 항목입니다.");
				$("input[name='cslFmTm']").focus();					return;
			}
			if($("input[name='cslToTm']").val() == ""){
				alert("집중 상담 시간은 필수 입력 항목입니다.");
				$("input[name='cslToTm']").focus();					return;
			}
			if(needTime($("input[name='cslFmTm']").val(), $("input[name='cslToTm']").val()) <= 0){
				alert("상담 소요시간은 0보다 값이 커야 합니다.");
				$("input[name='cslToTm']").focus();					return;
			}
			if($("input[name='cslSbj']").val() ==""){
				alert("상담주제는 필수 입력 항목입니다.");
				$("input[name='cslSbj']").focus();					return;
			}
			if($("textarea[name='cslCtnt']").val() == ""){
				alert("상담내용은 필수 입력 항목입니다.");
				$("textarea[name='cslCtnt']").focus();				return;
			}

			$.ajax({
				url : '/ajaxClsIdvAdd.do',
				type : 'POST',
				data : $('#cslForm').serialize(),
				success : function(res){
					if(res.err != "Y"){
						alert(res.MSG);
						getCslIdvList($("input[name='mbrNo']").val());
					}else{
						console.log("ERR")
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- ISP 수립 이력 조회 --%>
		getCslIspList = function(tagMbrNo){
			$.ajax({
				url : '/ajaxClsIspList.do',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo
				},
				success : function(res){
					if(res.err != "Y"){
						$("div#ispList").html("");

						if(res.clsIspList.length > 0){
							var inHtml = "<table id='clsIspTableList'>"
									   + "	<colgroup>"
									   + "		<col style='width:46px'>"
									   + "		<col style='width:100px'>"
									   + "		<col style='width:150px'>"
									   + "		<col style='width:1300px'>"
									   + "		<col style='width:150px'>"
									   + "		<col>"
									   + "	</colgroup>"
									   + "	<tbody>";

							$(res.clsIspList).each(function(idx, obj){
								inHtml = inHtml
									   + "	<tr id='" + idx + "'>"
									   + "		<td><div class='cell'>" + (idx + 1) + "</div></td>"
									   + "		<td><div class='cell'><a href='javaScript:viewIspRow(\"" + obj.ISP_DT + "\", \"" + obj.MBR_NO + "\");' class='row_link'>" + formatDate(obj.ISP_DT) + "</a></div></td>"
									   + "		<td><div class='cell'>" + obj.MNG_TP_NM + "</div></td>"
									   + "		<td class='txt-left'>"
									   + "			<div class='cell'>" + obj.ISP_RST + "</div>"
									   + "		</td>"
									   + "		<td><div class='cell'>" + obj.CRE_NM + "</div></td>"
									   + "		<td>"
									   + "			<div class='cell'>"
									   + "				<button type='button' onclick='javaScript:removeIsp(\"" + obj.ISP_DT + "\", \"" + obj.MBR_NO + "\", \"" + idx + "\");' class='el-button el-button--danger el-button--mini is-plain' style='margin-left: 1px; padding: 4px 9px;'>"
									   + "					<span>삭제</span>"
									   + "				</button>"
									   + "			</div>"
									   + "		</td>"
									   + "	</tr>";
							});

							inHtml = inHtml
								   + "	</tbody>"
								   + "</table>";

							$("div#ispList").html(inHtml);
						}else{
							$("div#ispList").html("<div class='no-data'>조회된 데이터가 없습니다.</div>");
						}
					}else{
						console.log("ERR")
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- ISP 상세 보기 --%>
		viewIspRow = function(tagIspDt, tagMbrNo){
			$.ajax({
				url : '/ajaxClsIspInfo.do',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo, 
					ispDt : tagIspDt
				},
				success : function(res){
					if(res.ispInfo != null){
						$("input[name='newFlag']").val("N");
						$("input[name='ispDt']").val(formatDate(res.ispInfo.ISP_DT));
						$("input[name='mngTpNm']").val(res.ispInfo.MNG_TP_NM);
						$("input[name='mngTpCd']").val(res.ispInfo.MNG_TP_CD);
						$("select[name='evlItmSco01']").val(res.ispInfo.EVL_ITM_SCO01).prop("selected", true);
						$("input[name='evlItmLnk01']").val(res.ispInfo.EVL_ITM_LNK01);
						$("select[name='evlItmSco02']").val(res.ispInfo.EVL_ITM_SCO02).prop("selected", true);
						$("input[name='evlItmLnk02']").val(res.ispInfo.EVL_ITM_LNK02);
						$("select[name='evlItmSco03']").val(res.ispInfo.EVL_ITM_SCO03).prop("selected", true);
						$("input[name='evlItmLnk03']").val(res.ispInfo.EVL_ITM_LNK03);
						$("select[name='evlItmSco04']").val(res.ispInfo.EVL_ITM_SCO04).prop("selected", true);
						$("input[name='evlItmLnk04']").val(res.ispInfo.EVL_ITM_LNK04);
						$("select[name='evlItmSco05']").val(res.ispInfo.EVL_ITM_SCO05).prop("selected", true);
						$("input[name='evlItmLnk05']").val(res.ispInfo.EVL_ITM_LNK05);
						$("select[name='evlItmSco06']").val(res.ispInfo.EVL_ITM_SCO06).prop("selected", true);
						$("input[name='evlItmLnk06']").val(res.ispInfo.EVL_ITM_LNK06);
						$("select[name='evlItmSco07']").val(res.ispInfo.EVL_ITM_SCO07).prop("selected", true);
						$("input[name='evlItmLnk07']").val(res.ispInfo.EVL_ITM_LNK07);
						$("select[name='evlItmSco08']").val(res.ispInfo.EVL_ITM_SCO08).prop("selected", true);
						$("input[name='evlItmLnk08']").val(res.ispInfo.EVL_ITM_LNK08);
						$("select[name='evlItmSco09']").val(res.ispInfo.EVL_ITM_SCO09).prop("selected", true);
						$("input[name='evlItmLnk09']").val(res.ispInfo.EVL_ITM_LNK09);
						$("select[name='evlItmSco10']").val(res.ispInfo.EVL_ITM_SCO10).prop("selected", true);
						$("input[name='evlItmLnk10']").val(res.ispInfo.EVL_ITM_LNK10);
						$("select[name='evlItmSco11']").val(res.ispInfo.EVL_ITM_SCO11).prop("selected", true);
						$("input[name='evlItmLnk11']").val(res.ispInfo.EVL_ITM_LNK11);
						$("select[name='evlItmSco12']").val(res.ispInfo.EVL_ITM_SCO12).prop("selected", true);
						$("input[name='evlItmLnk12']").val(res.ispInfo.EVL_ITM_LNK12);
						$("select[name='evlItmSco13']").val(res.ispInfo.EVL_ITM_SCO13).prop("selected", true);
						$("input[name='evlItmLnk13']").val(res.ispInfo.EVL_ITM_LNK13);
						$("select[name='evlItmSco14']").val(res.ispInfo.EVL_ITM_SCO14).prop("selected", true);
						$("input[name='evlItmLnk14']").val(res.ispInfo.EVL_ITM_LNK14);
						$("select[name='evlItmSco15']").val(res.ispInfo.EVL_ITM_SCO15).prop("selected", true);
						$("input[name='evlItmLnk15']").val(res.ispInfo.EVL_ITM_LNK15);
						$("select[name='evlItmSco16']").val(res.ispInfo.EVL_ITM_SCO16).prop("selected", true);
						$("input[name='evlItmLnk16']").val(res.ispInfo.EVL_ITM_LNK16);
						$("textarea[name='ispRst']").val(res.ispInfo.ISP_RST);
						$("textarea[name='tgtCtnt']").val(res.ispInfo.TGT_CTNT);

						for(var i=1 ; i<=16 ; i++){
							var tagName = "evlItmSco";
							if(i < 10){
								tagName = tagName + "0" + i;
							}else{
								tagName = tagName + i;
							}

							changEvlItemSco($("select[name='" + tagName + "']"));
						}
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- ISP 선택 삭제 --%>
		removeIsp = function(tagIspDt, tagMbrNo, idx){
			$.ajax({
				url : '/ajaxClsIspDel.do',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo, 
					ispDt : tagIspDt
				},
				success : function(res){
					if(res.err != "Y"){
						$("#clsIspTableList tr").each(function(){
							if($(this).attr("id") == idx){
								$(this).remove();
							}
						});

						if($("#clsIspTableList tr").length <= 0){
							$("div#ispList").html("<div class='no-data'>조회된 데이터가 없습니다.</div>");
						}
					}else{
						console.log(res.MSG);
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- ISP 저장 --%>
		saveIsp = function(){
			if($("input[name='mbrNo']").val() == ""){
				alert("회원을 선택하세요.");
				return;
			}

			$.ajax({
				url : '/ajaxCslIspAdd.do',
				type : 'POST',
				data : $('#cslForm').serialize(),
				success : function(res){
					if(res.err != "Y"){
						alert(res.MSG);
						getCslIspList($("input[name='mbrNo']").val());
					}else{
						alert(res.MSG);
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- ISP 신규 작성 --%>
		newIsp = function(){
			$("input[name='newFlag']").val("Y");
			$("input[name='ispDt']").val("");
			$("input[name='mngTpNm']").val("일시관리");
			$("input[name='mngTpCd']").val("10");
			$("select[name='evlItmSco01']").val("0").prop("selected", true);
			$("input[name='evlItmLnk01']").val("");
			$("select[name='evlItmSco02']").val("0").prop("selected", true);
			$("input[name='evlItmLnk02']").val("");
			$("select[name='evlItmSco03']").val("0").prop("selected", true);
			$("input[name='evlItmLnk03']").val("");
			$("select[name='evlItmSco04']").val("0").prop("selected", true);
			$("input[name='evlItmLnk04']").val("");
			$("select[name='evlItmSco05']").val("0").prop("selected", true);
			$("input[name='evlItmLnk05']").val("");
			$("select[name='evlItmSco06']").val("0").prop("selected", true);
			$("input[name='evlItmLnk06']").val("");
			$("select[name='evlItmSco07']").val("0").prop("selected", true);
			$("input[name='evlItmLnk07']").val("");
			$("select[name='evlItmSco08']").val("0").prop("selected", true);
			$("input[name='evlItmLnk08']").val("");
			$("select[name='evlItmSco09']").val("0").prop("selected", true);
			$("input[name='evlItmLnk09']").val("");
			$("select[name='evlItmSco10']").val("0").prop("selected", true);
			$("input[name='evlItmLnk10']").val("");
			$("select[name='evlItmSco11']").val("0").prop("selected", true);
			$("input[name='evlItmLnk11']").val("");
			$("select[name='evlItmSco12']").val("0").prop("selected", true);
			$("input[name='evlItmLnk12']").val("");
			$("select[name='evlItmSco13']").val("0").prop("selected", true);
			$("input[name='evlItmLnk13']").val("");
			$("select[name='evlItmSco14']").val("0").prop("selected", true);
			$("input[name='evlItmLnk14']").val("");
			$("select[name='evlItmSco15']").val("0").prop("selected", true);
			$("input[name='evlItmLnk15']").val("");
			$("select[name='evlItmSco16']").val("0").prop("selected", true);
			$("input[name='evlItmLnk16']").val("");
			$("textarea[name='ispRst']").val("");
			$("textarea[name='tgtCtnt']").val("");

			for(var i=1 ; i<=16 ; i++){
				var tagName = "evlItmSco";
				if(i < 10){
					tagName = tagName + "0" + i;
				}else{
					tagName = tagName + i;
				}

				changEvlItemSco($("select[name='" + tagName + "']"));
			}
		},
		<%-- 사정평가 이력 조회 --%>
		getCslAssList = function(tagMbrNo){
			$.ajax({
				url : '/ajaxCslAssInfo.do',
				type : 'POST',
				data : {
					mbrNo : tagMbrNo
				},
				success : function(res){
					var resultObj = res.cslAssInfo;

					if(resultObj != null){
						$("select[name='fstDrugCd']").val(resultObj.FST_DRUG_CD).prop("selected", true);
						$("input[name='fstDrug']").val(resultObj.FST_DRUG);
						$("select[name='mainDrugCd']").val(resultObj.MAIN_DRUG_CD).prop("selected", true);
						$("input[name='mainDrug']").val(resultObj.MAIN_DRUG);
						$("input[name='fstAge']").val(resultObj.FST_AGE);
						$("input[name='lstAge']").val(resultObj.LST_AGE);
						$("input[name='useTerm']").val(resultObj.USE_TERM);
						$("select[name='useFrqCd']").val(resultObj.USE_FRQ_CD).prop("selected", true);
						$("select[name='useCauCd']").val(resultObj.USE_CAU_CD).prop("selected", true);
						$("input[name='useCauEtc']").val(resultObj.USE_CAU_ETC);

						if(resultObj.LAW_PBM_CD.indexOf(",") >= 0){
							$.each(resultObj.LAW_PBM_CD.split(","), function(i, e){
								var cdVal = $.trim(e).replaceAll("[", "").replaceAll("]", "");
								$("select#lawPbmCd option[value=" + cdVal + "]").prop('selected', true);
							});
						}else{
							$("select[name='lawPbmCd']").val(resultObj.LAW_PBM_CD).prop("selected", true);
						}

						$("input[name='lawPbmEtc']").val(resultObj.LAW_PBM_ETC);
						$("input[name='physPbm']").val(resultObj.PHYS_PBM);
						$("select[name='sprtPbmCd']").val(resultObj.SPRT_PBM_CD).prop("selected", true);
						$("input[name='sprtPbmEtc']").val(resultObj.SPRT_PBM_ETC);

						if(resultObj.PRSN_CD != undefined){
							if(resultObj.PRSN_CD.indexOf(",") >= 0){
								$.each(resultObj.PRSN_CD.split(","), function(i, e){
									var cdVal = $.trim(e).replaceAll("[", "").replaceAll("]", "");
									$("select#prsnCd option[value=" + cdVal + "]").prop('selected', true);
								});
							}else{
								$("select[name='prsnCd']").val(resultObj.PRSN_CD).prop("selected", true);
							}
						}
						if(resultObj.EMTN_CD != undefined){
							if(resultObj.EMTN_CD.indexOf(",") >= 0){
								$.each(resultObj.EMTN_CD.split(","), function(i, e){
									var cdVal = $.trim(e).replaceAll("[", "").replaceAll("]", "");
									$("select#emtnCd option[value=" + cdVal + "]").prop('selected', true);
								});
							}else{
								$("select[name='emtnCd']").val(resultObj.EMTN_CD).prop("selected", true);
							}
						}
						if(resultObj.ACTN_CD != undefined){
							if(resultObj.ACTN_CD.indexOf(",") >= 0){
								$.each(resultObj.ACTN_CD.split(","), function(i, e){
									var cdVal = $.trim(e).replaceAll("[", "").replaceAll("]", "");
									$("select#actnCd option[value=" + cdVal + "]").prop('selected', true);
								});
							}else{
								$("select[name='actnCd']").val(resultObj.ACTN_CD).prop("selected", true);
							}
						}
						if(resultObj.FMLY_CD != undefined){
							if(resultObj.FMLY_CD.indexOf(",") >= 0){
								$.each(resultObj.FMLY_CD.split(","), function(i, e){
									var cdVal = $.trim(e).replaceAll("[", "").replaceAll("]", "");
									$("select#fmlyCd option[value=" + cdVal + "]").prop('selected', true);
								});
							}else{
								$("select[name='fmlyCd']").val(resultObj.FMLY_CD).prop("selected", true);
							}
						}
						if(resultObj.IT_RL_CD != undefined){
							if(resultObj.IT_RL_CD.indexOf(",") >= 0){
								$.each(resultObj.IT_RL_CD.split(","), function(i, e){
									var cdVal = $.trim(e).replaceAll("[", "").replaceAll("]", "");
									$("select#itRlCd option[value=" + cdVal + "]").prop('selected', true);
								});
							}else{
								$("select[name='itRlCd']").val(resultObj.IT_RL_CD).prop("selected", true);
							}
						}
						$("input[name='miEtc']").val(resultObj.MI_ETC);
						$("input[name='svrRcgDgr']").val(resultObj.SVR_RCG_DGR);
						$("textarea[name='expEfc']").val(resultObj.EXP_EFC);
						$("textarea[name='miMemo']").val(resultObj.MI_MEMO);
						$("select[name='evlTolCd']").val("").prop("selected", true);
						$("input[name='evlSco']").val("");
						$("textarea[name='evlCtnt']").val("");

						$("div#assEvlList").html("");

						if(res.cslAssEvlList != null && res.cslAssEvlList != ""){
							var inHtml = "<table id='assEvlTableList'>"
									   + "	<colgroup>"
									   + "		<col style='width:46px'>"
									   + "		<col style='width:90px'>"
									   + "		<col style='width:120px'>"
									   + "		<col style='width:100px'>"
									   + "		<col>"
									   + "	</colgroup>"
									   + "	<tbody>";

							for(var i=0 ; i<res.cslAssEvlList.length ; i++){
								var evlObj = res.cslAssEvlList[i];

								inHtml = inHtml
									   + "	<tr id='" + i + "'>"
									   + "		<td><div class='cell'>" + (i + 1) + "</div></td>"
									   + "		<td>"
									   + "			<div class='cell'>"
									   + "				<button type='button' onclick='javaScript:removeRowEval(\"" + i + "\", \"" + evlObj.EVL_SEQ + "\");' class='el-button el-button--danger el-button--mini is-plain' slot='reference' style='margin-left: 1px; padding: 4px 9px;'>"
									   + "					<i class='el-icon-delete'></i> <span>삭제</span>"
									   + "				</button>"
									   + "			</div>"
									   + "		</td>"
									   + "		<td><div class='cell'><a href='javaScript:choiceEval(\"" + evlObj.EVL_TOL_CD + "\", \"" + evlObj.EVL_SCO + "\", \"" + evlObj.EVL_CTNT + "\");' class='row_link'>" + evlObj.EVL_TOL_NM + "</a></div></td>"
									   + "		<td><div class='cell'>" + evlObj.EVL_SCO + "</div></td>"
									   + "		<td class='txt-left'><div class='cell'>" + evlObj.EVL_CTNT + "</div></td>"
									   + "	</tr>";

								$("input[name='evlIndex']").val(i);
							}

							inHtml = inHtml
								   + "	</tbody>"
								   + "</table>";
							$("div#assEvlList").html(inHtml);
						}else{
							$("div#assEvlList").html("<div class='no-data'>조회된 데이터가 없습니다.</div>");
						}
					}else{
						$("#assDivView").load(window.location.href + ' #assDivView', function(){
							$("button#assSaveButNo").hide();
							$("button#assSaveButYes").show();
						});
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- 사정평가 등록 --%>
		saveAss = function(){
			if($("input[name='mbrNo']").val() == ""){
				alert("회원을 선택하세요.");
				return;
			}
			if($("select[name='fstDrugCd']").val() == ""){
				alert("최초사용약물은 필수 입력 항목입니다.");
				$("select[name='fstDrugCd']").focus();						return;
			}
			if($("select[name='mainDrugCd']").val() == ""){
				alert("주요사용약물은 필수 입력 항목입니다.");
				$("select[name='mainDrugCd']").focus();						return;
			}
			if($("input[name='fstAge']").val() == ""){
				alert("최초사용시기는 필수 입력 항목입니다.");
				$("input[name='fstAge']").focus();							return;
			}
			if($("input[name='lstAge']").val() == ""){
				alert("마지막사용시기는 필수 입력 항목입니다.");
				$("input[name='lstAge']").focus();							return;
			}
			if($("input[name='useTerm']").val() == ""){
				alert("사용기간은 0보다 값이 커야 합니다.");
				$("input[name='useTerm']").focus();							return;
			}
			if($("select[name='useFrqCd']").val() == ""){
				alert("사용빈도는 필수 입력 항목입니다.");
				$("select[name='useFrqCd']").focus();						return;
			}
			if($("select[name='useCauCd']").val() == ""){
				alert("사용원인은 필수 입력 항목입니다.");
				$("select[name='useCauCd']").focus();						return;
			}
			if($("select[name='lawPbmCd']").val() == ""){
				alert("약물관련 법적문제는 필수 입력 항목입니다.");
				$("select[name='lawPbmCd']").focus();						return;
			}
			if($("input[name='physPbm']").val() == ""){
				alert("신체적 건강문제는 필수 입력 항목입니다.");
				$("input[name='physPbm']").focus();							return;
			}
			if($("select[name='sprtPbmCd']").val() == ""){
				alert("정신적 건강문제는 필수 입력 항목입니다.");
				$("select[name='sprtPbmCd']").focus();						return;
			}

			$.ajax({
				url : '/ajaxCslAssAdd.do',
				type : 'POST',
				data : $('#cslForm').serialize(),
				success : function(res){
					if(res.err != 'Y'){
						alert("사정평가 " + res.MSG + " 완료");
						getCslAssList($("input[name='mbrNo']").val());
					}else{
						alert(res.MSG);
					}
				},
				error : function(xhr, status){}
			});
		},
		<%-- 사정평가 평가도구 삭제 --%>
		removeRowEval = function(rowNum, tagEvlSeq){
			if(tagEvlSeq != 0){
				if($("input[name='deleteEvlSeq']").val() != ""){
					$("input[name='deleteEvlSeq']").val($("input[name='deleteEvlSeq']").val() + "," + tagEvlSeq);
				}else{
					$("input[name='deleteEvlSeq']").val(tagEvlSeq);
				}
			}

			$("table#assEvlTableList tr").each(function(){
				if($(this).attr("id") == rowNum){
					$(this).remove();
				}
			});

			if($("table#assEvlTableList tr").length <= 0){
				$("div#assEvlList").html("<div class='no-data'>조회된 데이터가 없습니다.</div>");
			}
		},
		<%-- 사정평가 평가도구 상세보기 --%>
		choiceEval = function(tagEvlTolCd, tagEvlSco, tagEvlCtnt){
			$("select[name='evlTolCd']").val(tagEvlTolCd).prop("selected", true);
			$("input[name='evlSco']").val(tagEvlSco);
			$("textarea[name='evlCtnt']").val(tagEvlCtnt);
		},
		<%-- 사정평가 평가도구 등록 --%>
		addRowEval = function(){
			var index = Number($("input[name='evlIndex']").val()) + 1;
			var mbrNo = $("input[name='mbrNo']").val();
			var evlTolCd = $("select[name='evlTolCd']").val();
			var evlSco = $("input[name='evlSco']").val();
			var evlCtnt = $("textarea[name='evlCtnt']").val();

			if(mbrNo == ""){
				alert("회원을 선택하세요.");						return;
			}
			if(evlTolCd == ""){
				alert("평가도구를 선택 하세요.");					return;
			}
			if(evlSco == ""){
				alert("평가점수를 입력 하세요.");					return;
			}
			if(evlCtnt == ""){
				alert("평가내용을 입력 하세요.");					return;
			}

			if($("table#assEvlTableList tr").length > 0){
				var inHtml = "	<tr id='" + index + "'>"
						   + "	<input type='hidden' name='evlTolCdHidden' value='" + evlTolCd + "' />"
						   + "	<input type='hidden' name='evlScoHidden' value='" + evlSco + "' />"
						   + "	<input type='hidden' name='evlCtntHidden' value='" + evlCtnt + "' />"
						   + "		<td><div class='cell'>" + (index + 1) + "</div></td>"
						   + "		<td>"
						   + "			<div class='cell'>"
						   + "				<button type='button' onclick='javaScript:removeRowEval(\"" + index + "\", \"0\");' class='el-button el-button--danger el-button--mini is-plain' slot='reference' style='margin-left: 1px; padding: 4px 9px;'>"
						   + "					<i class='el-icon-delete'></i> <span>삭제</span>"
						   + "				</button>"
						   + "			</div>"
						   + "		</td>"
						   + "		<td><div class='cell'><a href='javaScript:choiceEval(\"" + evlTolCd + "\", \"" + evlSco + "\", \"" + evlCtnt + "\");' class='row_link'>" + $("select[name='evlTolCd'] option:selected").text() + "</a></div></td>"
						   + "		<td><div class='cell'>" + evlSco + "</div></td>"
						   + "		<td class='txt-left'><div class='cell'>" + evlCtnt + "</div></td>"
						   + "	</tr>";
	
				$("table#assEvlTableList > tbody:last").append(inHtml);
			}else{
				var inHtml = "<table id='assEvlTableList'>"
						   + "	<colgroup>"
						   + "		<col style='width:46px'>"
						   + "		<col style='width:90px'>"
						   + "		<col style='width:120px'>"
						   + "		<col style='width:100px'>"
						   + "		<col>"
						   + "	</colgroup>"
						   + "	<tbody>"
						   + "	<tr id='" + index + "'>"
						   + "	<input type='hidden' name='evlTolCdHidden' value='" + evlTolCd + "' />"
						   + "	<input type='hidden' name='evlScoHidden' value='" + evlSco + "' />"
						   + "	<input type='hidden' name='evlCtntHidden' value='" + evlCtnt + "' />"
						   + "		<td><div class='cell'>" + (index + 1) + "</div></td>"
						   + "		<td>"
						   + "			<div class='cell'>"
						   + "				<button type='button' onclick='javaScript:removeRowEval(\"" + index + "\", \"0\");' class='el-button el-button--danger el-button--mini is-plain' slot='reference' style='margin-left: 1px; padding: 4px 9px;'>"
						   + "					<i class='el-icon-delete'></i> <span>삭제</span>"
						   + "				</button>"
						   + "			</div>"
						   + "		</td>"
						   + "		<td><div class='cell'><a href='javaScript:choiceEval(\"" + evlTolCd + "\", \"" + evlSco + "\", \"" + evlCtnt + "\");' class='row_link'>" + $("select[name='evlTolCd'] option:selected").text() + "</a></div></td>"
						   + "		<td><div class='cell'>" + evlSco + "</div></td>"
						   + "		<td class='txt-left'><div class='cell'>" + evlCtnt + "</div></td>"
						   + "	</tr>"
						   + "	</tbody>"
						   + "</table>";

				$("div#assEvlList").html(inHtml);
			}
			$("input[name='evlIndex']").val(index);
		},
		<%-- 상담 소요시간 계산 --%>
		changTime = function(tagName, val){
			$("input[name='" + tagName + "']").val(val);

			if($("input[name='cslFmTm']").val() != "" && $("input[name='cslToTm']").val() != ""){
				var termTm = needTime($("input[name='cslFmTm']").val(), $("input[name='cslToTm']").val());
				if(termTm > 0){
					$("span#cslTermTm").text(termTm);
					$("input[name='cslTermTm']").val(termTm);
				}
			}
		},
		<%-- 위기분류척도 점수 계산--%>
		changRating = function(){
			var ratingNum = 0;

			ratingNum += Number($("select[name='rskaTpCd'] option:selected").attr("rating"));
			ratingNum += Number($("select[name='rskbTpCd'] option:selected").attr("rating"));
			ratingNum += Number($("select[name='rskcTpCd'] option:selected").attr("rating"));

			$("#ratingNum").text(ratingNum);
			$("input[name='rskSco']").val(ratingNum);
		},
		<%-- 상담내용보기 --%>
		ctntPopup = function(tagName){
			$("input[name='tagName']").val(tagName);
			$("textarea[name='viewCslCtnt']").val($("textarea[name='" + tagName + "']").val());
			layerPopupOpen('counwrite');
		},
		<%-- 상담내용상세보기 확인 --%>
		setCslCtnt = function(){
			$("textarea[name='" + $("input[name='tagName']").val() + "']").val($("textarea[name='viewCslCtnt']").val());
			layerPopupClose('counwrite');
		},
		<%-- ISP수립 문항 수정 --%>
		changEvlItemSco = function(obj){
			var tagName = $(obj).attr("name");
			var rating = $("select[name='" + tagName + "'] option:selected").attr("rating");

			if(rating == 4){
				$("th#" + tagName).attr("class", "bg-pr");
			}else if(rating >= 2 && rating <= 3){
				$("th#" + tagName).attr("class", "bg-og");
			}else{
				$("th#" + tagName).attr("class", "bg-pk");
			}

			checkMngTp();
		},
		<%-- 관리구분명 수정 --%>
		checkMngTp = function(){
			var maxRating = 0;
			var mngTpCd = Number($("input[name='mngTpCd']").val());

<c:if test="${mngTpList ne null && mngTpList ne ''}">
			var mngTpNmList = [
	<c:forEach var="result" items="${mngTpList}" varStatus="status">
				'<c:out value="${result.CD_NM}" />',
	</c:forEach>
			];
</c:if>

			$("table#mngTpTable").find("select").each(function(idx){
				var tagName = $(this).attr("name");
				var rating = $("select[name='" + tagName + "'] option:selected").attr("rating");

				if(rating > maxRating){
					maxRating = rating;
				}
			});

			$("input[name='mngTpCd']").val(maxRating * 10);
			$("input[name='mngTpNm']").val(mngTpNmList[maxRating - 1]);
		},
		<%-- 탭 메뉴 활성화 --%>
		$('.el-tabs__item').on('click', function(){
			var id = $(this).attr('data-id');
			$(this).addClass('is-active').siblings().removeClass('is-active');

			$('.tab-form').hide();
			$('#' + id).show();
		});
	});
</script>
<!-- 페이지 타이틀 -->
<div class="tit-area">
	<h1><i class="el-icon-s-order" style="color: rgb(0, 108, 185);"></i> 개별 회복지원 서비스 	</h1>
</div>
<!-- // 페이지 타이틀 -->
<!-- 상단 버튼 -->
<div class="top-right-btn">
	<button type="button" onclick="javaScript:individualNew();" class="el-button normal el-button--default el-button--small is-plain" style="margin-left: 8px;">
		<i class="el-icon-refresh"></i> <span>초기화</span>
	</button>
	<button id="excelNo" type="button" disabled="disabled" class="el-button normal el-button--default el-button--small is-disabled is-plain">
		<i class="el-icon-document"></i> <span>엑셀다운로드</span>
	</button>
	<button id="excelYes" onclick="javaScript:individualExel();" type="button" class="el-button normal el-button--default el-button--small is-plain" style="display:none;">
		<i class="el-icon-document"></i> <span>엑셀다운로드</span>
	</button>
</div>
<!-- // 상단 버튼 -->
<form name="cslForm" id="cslForm">
<form:input path="cslIdvInfo.cslNo" hidden="true" />
<form:input path="cslIdvInfo.cslTermTm" hidden="true" />
<input type="hidden" name="newFlag" value="Y" />
<input type="hidden" name="evlIndex" value="0" />
<input type="hidden" name="deleteEvlSeq" />
<div class="formline">
	<!-- 회원등록번호 ~ 사례관리자 -->
	<div class="section">
		<table class="w-auto">
			<tbody>
			<tr>
				<th>회원등록번호</th>
				<td>
					<div class="search-input tac">
						<form:input path="mstMbrInfo.mbrNo" cssClass="el-input__inner" readonly="true" placeholder="회원등록번호" style="width: 142px;" />
						<button type="button" onclick="javaScript:mstMbrSearchPopup();"><i class="el-icon-search"></i></button>
					</div>
				</td>
				<th>성명</th>
				<td>
					<div class="dsp-ibk tac"><form:input path="mstMbrInfo.mbrNm" cssClass="el-input__inner" readonly="true" placeholder="성명" style="width: 100px;" /></div>
				</td>
				<th>성별</th>
				<td>
					<div class="dsp-ibk tac"><form:input path="mstMbrInfo.gendNm" cssClass="el-input__inner" readonly="true" placeholder="성별" style="width: 50px;" /></div>
				</td>
				<th>연령</th>
				<td>
					<div class="dsp-ibk tac"><form:input path="mstMbrInfo.age" cssClass="el-input__inner" readonly="true" maxlength="3" placeholder="연령" style="width: 50px;" /></div> (세)
				</td>
				<th>등록일자</th>
				<td>
					<div class="dsp-ibk tac"><form:input path="mstMbrInfo.regDt" cssClass="el-input__inner" readonly="true" placeholder="등록일자" style="width: 100px;" /></div>
				</td>
				<th>의료보장</th>
				<td>
					<div class="dsp-ibk is-disabled"><form:input path="mstMbrInfo.medicCareNm" cssClass="el-input__inner" readonly="true" placeholder="의료보장" style="width: 120px;" /></div>
				</td>
				<th>사례관리자</th>
				<td>
					<div class="dsp-ibk is-disabled"><form:input path="mstMbrInfo.mngUsrId" cssClass="el-input__inner" readonly="true" placeholder="사례관리자" style="width: 130px;" /></div>
				</td>
			</tr>
		</tbody>
	</table>
</div>
<!-- // 회원등록번호 ~ 사례관리자 -->
<!-- 집중상담, ISP수립, 사정평가 -->
<div class="section-sha" style="min-width: 1840px;">
	<!-- 탭메뉴 -->
	<div class="el-tabs__header is-top">
		<div class="el-tabs__nav-wrap is-top">
			<div class="el-tabs__nav-scroll">
				<div role="tablist" class="el-tabs__nav is-top">
					<div class="el-tabs__item is-top is-active" data-id="tab-focus">
						<span><i class="el-icon-s-help"></i> 집중상담</span>
					</div>
					<div class="el-tabs__item is-top" data-id="tab-isp">
						<span><i class="el-icon-s-management"></i> ISP 수립</span>
					</div>
					<div class="el-tabs__item is-top" data-id="tab-assessment">
						<span><i class="el-icon-platform-eleme"></i> 사정평가</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- // 탭메뉴 -->
	<div class="el-tabs_content">
		<!-- 집중상담 -->
		<div id="tab-focus" class="tab-form">
			<div class="in-tab-btn">
				<button disabled="disabled" id="idvSaveBtnNo" type="button" class="el-button normal el-button--primary el-button--small is-disabled is-plain" style="padding: 7px 13px;">
					<i class="el-icon-download"></i> <span>저장</span>
				</button>
				<button type="button" id="idvSaveBtnYes" onclick="javaScript:saveIdv();" class="el-button normal el-button--primary el-button--small is-plain" style="padding: 7px 13px;display: none;">
					<i class="el-icon-download"></i> <span>저장</span>
				</button>
				<button type="button" onclick="javaScript:newIdv();" class="el-button el-button--default el-button--small is-plain" style="padding: 7px 13px;">
					<i class="el-icon-circle-plus-outline"></i> <span>신규</span>
				</button>
			</div>
			<div class="tab-tb-box">
				<div class="table-box">
					<div class="el-table_header-wrapper">
						<table>
							<colgroup>
								<col style="width:46px">
								<col style="width:150px">
								<col style="width:100px">
								<col style="width:120px">
								<col style="width:80px">
								<col style="width:400px">
								<col style="width:690px">
								<col style="width:150px">
								<col>
							</colgroup>
							<thead>
							<tr>
								<th>#</th>
								<th>상담 번호</th>
								<th>상담 일자</th>
								<th>시작/종료 시간</th>
								<th>소요(분)</th>
								<th>상담주제</th>
								<th>상담목표</th>
								<th>상담자</th>
								<th>작업</th>
							</tr>
							</thead>
						</table>
					</div>
					<div class="el-table_body-wrapper" style="height: 148px;" id="idvList">
						<div class="no-data">조회된 데이터가 없습니다.</div>
					</div>
				</div>
			</div>
			<div class="bottom-form el-row" id="idvInfoDiv">
				<div class="row">
					<table class="w-auto wr-form">
						<tbody>
						<tr>
							<th>상담자</th>
							<td>
								<div class="dsp-ibk is-disabled"><form:input path="cslIdvInfo.cslNm" cssClass="el-input__inner" readonly="true" style="width: 120px;" /></div>
							</td>
							<th><span class="required">*</span> 상담일시</th>
							<td>
								<div class="dat-pk">
									<i class="el-input__icon el-icon-date"></i>
									<form:input path="cslIdvInfo.cslDt" cssClass="el-input__inner datepicker" placeholder="날짜 선택" style="width: 130px;" />
								</div>
								<div class="time-box">
									<div class="tm-in">
										<i class="el-input__icon el-icon-time"></i>
										<form:input path="cslIdvInfo.cslFmTm" value="09:00" cssClass="el-input__inner timepicker" placeholder="시작" style="width: 96px;" />
									</div>
									<span>~</span>
									<div class="tm-in">
										<i class="el-input__icon el-icon-time"></i>
										<form:input path="cslIdvInfo.cslToTm" value="09:00" cssClass="el-input__inner timepicker" placeholder="종료" style="width: 96px;" />
									</div>
									<div class="t-min">
										<span class="readonly" id="cslTermTm">0</span> 분 소요
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th><span class="required">*</span> 상담대상</th>
							<td>
<c:forEach var="result" items="${cslTgtCdList}" varStatus="status">
								<span class="ck-bx">
									<input type="radio" class="el-radio__original" name="cslTgtCd" id="cslTgtCd-<c:out value='${status.count}'/>"  value="${result.CD_ID}"<c:if test="${result.CD_ID eq cslIdvInfo.cslTgtCd}"> checked</c:if> />
									<label for="cslTgtCd-<c:out value='${status.count}'/>"><span class="el-radio__input"><span class="el-radio__inner"></span></span> <c:out value="${result.CD_NM}" /></label>
								</span>
</c:forEach>
							</td>
							<th><span class="required">*</span> 상담유형</th>
							<td>
<c:forEach var="result" items="${cslTpCdList}" varStatus="status">
								<span class="ck-bx">
									<input type="radio" class="el-radio__original" name="cslTpCd" id="cslTpCd-<c:out value='${status.count}'/>"value="${result.CD_ID}"<c:if test="${result.CD_ID eq cslIdvInfo.cslTpCd}"> checked</c:if> />
									<label for="cslTpCd-<c:out value='${status.count}'/>"><span class="el-radio__input"><span class="el-radio__inner"></span></span> <c:out value="${result.CD_NM}" /></label>
								</span>
</c:forEach>
							</td>
						</tr>
						<tr>
							<th><span class="required">*</span> 상담주제</th>
							<td colspan="3"><form:input path="cslIdvInfo.cslSbj" cssClass="el-input__inner" placeholder="상담주제" style="width: 680px;" /></td>
						</tr>
						<tr>
							<th>상담목표</th>
							<td colspan="3"><form:input path="cslIdvInfo.cslTgt" cssClass="el-input__inner" placeholder="상담목표" style="width: 680px;" /></td>
						</tr>
						<tr>
							<th class="v-top">
								<span class="required">*</span> 상담내용<br>
								<button type="button" onclick="javaScript:ctntPopup('cslCtnt');" class="el-button el-button--success el-button--mini is-plain" style="padding: 4px 6px;">
									<i class="el-icon-search"></i>
								</button>
							</th>
							<td colspan="3"><textarea name="cslCtnt" placeholder="상담 내용" style="width:100%;height:385px">${cslIdvInfo.cslCtnt}</textarea></td>
						</tr>
						</tbody>
					</table>
				</div>
				<div class="row">
					<table class="w-auto wr-form">
						<tbody>
						<tr>
							<th><span class="required">*</span> 위기분류척도</th>
							<td>
								<span class="el-tag">Rating A: 위험성</span>
								<select name="rskaTpCd" style="width: 690px;" onchange="javaScript:changRating();">
<c:forEach var="result" items="${rskaTpList}" varStatus="status">
									<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th class="txt-center">점수</th>
							<td>
								<span class="el-tag">Rating B: 지지체계</span>
								<select name="rskbTpCd" style="width: 690px;" onchange="javaScript:changRating();">
<c:forEach var="result" items="${rskbTpList}" varStatus="status">
									<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th class="txt-center"> <span class="el-tag-danger" id="ratingNum"><c:out value="${cslIdvInfo.rskSco}" /></span></th>
							<td>
								<span class="el-tag">Rating B: 지지체계</span>
								<select name="rskcTpCd" style="width: 690px;" onchange="javaScript:changRating();">
<c:forEach var="result" items="${rskcTpList}" varStatus="status">
									<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th>다음 상담일시</th>
							<td>
								<div class="dat-pk">
									<i class="el-input__icon el-icon-date"></i>
									<form:input path="cslIdvInfo.nxtCslDt" cssClass="el-input__inner datepicker" placeholder="날짜" style="width: 130px;" />
								</div>
								<div class="time-box">
									<div class="tm-in">
										<i class="el-input__icon el-icon-time"></i>
										<form:input path="cslIdvInfo.nxtCslTm" cssClass="el-input__inner timepicker" placeholder="시간" style="width: 96px;" />
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th class="v-top">
								다음 상담내용<br>
								<button type="button" onclick="javaScript:ctntPopup('nxtCslCtnt');" class="el-button el-button--success el-button--mini is-plain" style="padding: 4px 6px;">
									<i class="el-icon-search"></i>
								</button>
							</th>
							<td><textarea name="nxtCslCtnt" placeholder="다음 상담내용" style="width:100%;height:385px"><c:out value="${cslIdvInfo.nxtCslCtnt}" /></textarea></td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- // 집중상담 -->

		<!-- ISP 수립-->
		<div id="tab-isp" class="tab-form" style="display: none;">
			<div class="in-tab-btn">
				<button disabled="disabled" type="button" id="ispSaveButNo" class="el-button normal el-button--primary el-button--small is-disabled is-plain" style="padding: 7px 13px;">
					<i class="el-icon-download"></i> <span>저장</span>
				</button>
				<button type="button" onclick="javaScript:saveIsp();" id="ispSaveButYes" class="el-button normal el-button--primary el-button--small is-plain" style="padding: 7px 13px;display: none;">
					<i class="el-icon-download"></i> <span>저장</span>
				</button>
				<button type="button" onclick="javaScript:newIsp();" class="el-button el-button--default el-button--small is-plain" style="padding: 7px 13px;">
					<i class="el-icon-circle-plus-outline"></i> <span>신규</span>
				</button>
			</div>
			<div class="tab-tb-box">
				<div class="table-box">
					<div class="el-table_header-wrapper">
						<table>
							<colgroup>
								<col style="width:46px">
								<col style="width:100px">
								<col style="width:150px">
								<col style="width:1300px">
								<col style="width:150px">
								<col>
							</colgroup>
							<thead>
							<tr>
								<th>#</th>
								<th>수립 일자</th>
								<th>관리구분</th>
								<th>ISP 결과</th>
								<th>등록자</th>
								<th>작업</th>
							</tr>
							</thead>
						</table>
					</div>
					<div class="el-table_body-wrapper" style="height: 148px;" id="ispList">
						<div class="no-data">조회된 데이터가 없습니다.</div>
					</div>
				</div>
			</div>
			<div class="bottom-form el-row" id="ispInfoDiv">
				<div class="section">
					<table class="w-auto wr-form">
						<tbody>
						<tr>
							<th><span class="required">*</span> ISP 일자</th>
							<td>
								<div class="dat-pk">
									<i class="el-input__icon el-icon-date"></i>
									<form:input path="cslIspInfo.ispDt" cssClass="el-input__inner datepicker" placeholder="날짜 선택" style="width: 130px;" />
								</div>
							</td>
							<th>관리구분</th>
							<td>
								<div class="dsp-ibk is-disabled">
									<form:input path="cslIspInfo.mngTpNm" cssClass="el-input__inner" placeholder="일시관리" readonly="true" style="width: 150px;" />
									<form:input path="cslIspInfo.mngTpCd" style="display: none;" />
								</div>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
				<div class="multi-table">
					<table class="wr-table" id="mngTpTable">
						<colgroup>
							<col style="width: 8%;">
							<col style="width: 4%;">
							<col style="width: 13%;">
							<col style="width: 8%;">
							<col style="width: 4%;">
							<col style="width: 13%;">
							<col style="width: 8%;">
							<col style="width: 4%;">
							<col style="width: 13%;">
							<col style="width: 8%;">
							<col style="width: 4%;">
							<col style="width: 13%;">
						</colgroup>
						<thead>
						<tr>
							<th colspan="3">중독 평가</th>
							<th colspan="3">상태 평가</th>
							<th colspan="3">사회적 기능 평가</th>
							<th colspan="3">사회 서비스 평가</th>
						</tr>
						<tr>
							<th>문항</th>
							<th>심각도</th>
							<th>자원연계</th>
							<th>문항</th>
							<th>심각도</th>
							<th>자원연계</th>
							<th>문항</th>
							<th>심각도</th>
							<th>자원연계</th>
							<th>문항</th>
							<th>심각도</th>
							<th>자원연계</th>
						</tr>
						</thead>
						<tbody>
							<tr>
								<th id="evlItmSco01" class="bg-pk">약물사용문제</th>
								<td>
									<select name="evlItmSco01" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco01List ne null and evlItmSco01List ne ''}">
	<c:forEach var="result" items="${evlItmSco01List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><form:input path="cslIspInfo.evlItmLnk01" cssClass="el-input__inner" style="width: 100%;" /></td>
								<th id="evlItmSco05" class="bg-pk">자해 및 타해 위험</th>
								<td>
									<select name="evlItmSco05" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco01List ne null and evlItmSco01List ne ''}">
	<c:forEach var="result" items="${evlItmSco01List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><form:input path="cslIspInfo.evlItmLnk05" cssClass="el-input__inner" style="width: 100%;" /></td>
								<th id="evlItmSco10" class="bg-pk">가족관계</th>
								<td>
									<select name="evlItmSco10" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco10List ne null and evlItmSco10List ne ''}">
	<c:forEach var="result" items="${evlItmSco10List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><form:input path="cslIspInfo.evlItmLnk10" cssClass="el-input__inner" style="width: 100%;" /></td>
								<th id="evlItmSco12" class="bg-pk">주거</th>
								<td>
									<select name="evlItmSco12" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco12List ne null and evlItmSco12List ne ''}">
	<c:forEach var="result" items="${evlItmSco12List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><form:input path="cslIspInfo.evlItmLnk12" cssClass="el-input__inner" style="width: 100%;" /></td>
							</tr>
							<tr>
								<th id="evlItmSco02" class="bg-pk">알코올사용문제</th>
								<td>
									<select name="evlItmSco02" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco02List ne null and evlItmSco02List ne ''}">
	<c:forEach var="result" items="${evlItmSco02List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><form:input path="cslIspInfo.evlItmLnk02" cssClass="el-input__inner" style="width: 100%;" /></td>
								<th id="evlItmSco06" class="bg-pk">정신과적 증상</th>
								<td>
									<select name="evlItmSco06" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco06List ne null and evlItmSco06List ne ''}">
	<c:forEach var="result" items="${evlItmSco06List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><form:input path="cslIspInfo.evlItmLnk06" cssClass="el-input__inner" style="width: 100%;" /></td>
								<th id="evlItmSco11" class="bg-pk">사회적관계</th>
								<td>
									<select name="evlItmSco11" style="width: 100%; onchange="javaScript:changEvlItemSco(this);"">
<c:if test="${evlItmSco11List ne null and evlItmSco11List ne ''}">
	<c:forEach var="result" items="${evlItmSco11List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><form:input path="cslIspInfo.evlItmLnk11" cssClass="el-input__inner" style="width: 100%;" /></td>
								<th id="evlItmSco13" class="bg-pk">경제활동 지원</th>
								<td>
									<select name="evlItmSco13" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco13List ne null and evlItmSco13List ne ''}">
	<c:forEach var="result" items="${evlItmSco13List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><form:input path="cslIspInfo.evlItmLnk13" cssClass="el-input__inner" style="width: 100%;" /></td>
							</tr>
							<tr>
								<th id="evlItmSco03" class="bg-pk">도박사용문제</th>
								<td>
									<select name="evlItmSco03" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco03List ne null and evlItmSco03List ne ''}">
	<c:forEach var="result" items="${evlItmSco03List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><form:input path="cslIspInfo.evlItmLnk03" cssClass="el-input__inner" style="width: 100%;" /></td>
								<th id="evlItmSco07" class="bg-pk">정신약물관리</th>
								<td>
									<select name="evlItmSco07" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco07List ne null and evlItmSco07List ne ''}">
	<c:forEach var="result" items="${evlItmSco07List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><form:input path="cslIspInfo.evlItmLnk07" cssClass="el-input__inner" style="width: 100%;" /></td>
								<td colspan="3"></td>
								<th id="evlItmSco14" class="bg-pk">취업 및 학업욕구</th>
								<td>
									<select name="evlItmSco14" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco07List ne null and evlItmSco07List ne ''}">
	<c:forEach var="result" items="${evlItmSco07List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><form:input path="cslIspInfo.evlItmLnk14" cssClass="el-input__inner" style="width: 100%;" /></td>
							</tr>
							<tr>
								<th id="evlItmSco04" class="bg-pk">인터넷사용문제</th>
								<td>
									<select name="evlItmSco04" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco04List ne null and evlItmSco04List ne ''}">
	<c:forEach var="result" items="${evlItmSco04List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><form:input path="cslIspInfo.evlItmLnk04" cssClass="el-input__inner" style="width: 100%;" /></td>
								<th id="evlItmSco08" class="bg-pk">스트레스 상태</th>
								<td>
									<select name="evlItmSco08" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco08List ne null and evlItmSco08List ne ''}">
	<c:forEach var="result" items="${evlItmSco08List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><form:input path="cslIspInfo.evlItmLnk08" cssClass="el-input__inner" style="width: 100%;" /></td>
								<td colspan="3"></td>
								<th id="evlItmSco15" class="bg-pk">고용 및 교육가능성</th>
								<td>
									<select name="evlItmSco15" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco15List ne null and evlItmSco15List ne ''}">
	<c:forEach var="result" items="${evlItmSco15List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><form:input path="cslIspInfo.evlItmLnk15" cssClass="el-input__inner" style="width: 100%;" /></td>
							</tr>
							<tr>
								<td colspan="3"></td>
								<th id="evlItmSco09" class="bg-pk">신체질환</th>
								<td>
									<select name="evlItmSco09" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco09List ne null and evlItmSco09List ne ''}">
	<c:forEach var="result" items="${evlItmSco09List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><form:input path="cslIspInfo.evlItmLnk09" cssClass="el-input__inner" style="width: 100%;" /></td>
								<td colspan="3"></td>
								<th id="evlItmSco16" class="bg-pk">법률적 문제</th>
								<td>
									<select name="evlItmSco16" style="width: 100%;" onchange="javaScript:changEvlItemSco(this);">
<c:if test="${evlItmSco16List ne null and evlItmSco16List ne ''}">
	<c:forEach var="result" items="${evlItmSco16List}" varStatus="status">
										<option value="<c:out value="${result.CD_ID}"/>" rating="<c:out value='${status.index}' />"><c:out value="${result.CD_NM}" /></option>
	</c:forEach>
</c:if>
									</select>
								</td>
								<td><form:input path="cslIspInfo.evlItmLnk16" cssClass="el-input__inner" style="width: 100%;" /></td>
							</tr>
							</tbody>
						</table>
					</div>
					<div class="el-row">
						<div class="row2">
							<div class="section pdn">
								<div class="el-card_header">
									<h2><i class="el-icon-s-opportunity"></i> ISP 결과</h2>
								</div>
								<div class="el-card_body"><textarea name="ispRst" style="height: 162px;" placeholder="ISP 결과"></textarea></div>
							</div>
						</div>
						<div class="row2">
							<div class="section pdn">
								<div class="el-card_header">
									<h2><i class="el-icon-s-opportunity"></i> 장단기 목표수립</h2>
								</div>
								<div class="el-card_body"><textarea name="tgtCtnt" style="height: 162px;" placeholder="장단기 목표수립"></textarea></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- // ISP 수립-->

			<!-- 사정평가 -->
			<div id="tab-assessment" class="tab-form" style="display: none;"><div id="assDivView">
				<div class="in-tab-btn">
					<button disabled="disabled" type="button" id="assSaveButNo" class="el-button normal el-button--primary el-button--small is-disabled is-plain" style="padding: 7px 13px;">
						<i class="el-icon-download"></i> <span>저장</span>
					</button>
					<button type="button" onclick="javaScript:saveAss();" id="assSaveButYes" class="el-button normal el-button--primary el-button--small is-plain" style="padding: 7px 13px;display: none;">
						<i class="el-icon-download"></i> <span>저장</span>
					</button>
				</div>
				<div class="el-row">
					<div class="row2">
						<div class="section pdn">
							<div class="el-card_header">
								<h2><i class="el-icon-s-opportunity"></i> 중독력</h2>
							</div>
							<div class="el-card_body">
								<table class="w-auto wr-form">
									<tbody>
									<tr>
										<th><span class="required">*</span> 최초 사용약물</th>
										<td colspan="5">
											<select name="fstDrugCd" style="width: 100%;">
												<option value="">선택</option>
<c:forEach var="result" items="${fstDrugCdList}" varStatus="status">
												<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
											</select>
											<div style="margin-top:5px"><form:input path="cslAssInfo.fstDrug" cssClass="el-input__inner" style="width: 100%;" placeholder="최초사용약물 입력" /></div>
										</td>
									</tr>
									<tr>
										<th><span class="required">*</span> 주요 사용약물</th>
										<td colspan="5">
											<select name="mainDrugCd" style="width: 100%;">
												<option value="">선택</option>
<c:forEach var="result" items="${mainDrugCdList}" varStatus="status">
												<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
											</select>
											<div style="margin-top:5px"><form:input path="cslAssInfo.mainDrug" cssClass="el-input__inner" style="width: 100%;" placeholder="주요사용약물 입력" /></div>
										</td>
									</tr>
									<tr>
										<th><span class="required">*</span> 최초 사용시기</th>
										<td><form:input path="cslAssInfo.fstAge" cssClass="el-input__inner" placeholder="나이" style="width: 100px;" /></td>
										<th><span class="required">*</span> 마지막 사용시기</th>
										<td><form:input path="cslAssInfo.lstAge" cssClass="el-input__inner" placeholder="나이" style="width: 100px;" /></td>
										<th><span class="required">*</span> 사용기간</th>
										<td><form:input path="cslAssInfo.useTerm" cssClass="el-input__inner" placeholder="사용기간" style="width: 113px;" /></td>
									</tr>
									<tr>
										<th><span class="required">*</span> 사용빈도</th>
										<td>
											<select name="useFrqCd" style="width:150px">
												<option value="">선택</option>
<c:forEach var="result" items="${useFrqCdList}" varStatus="status">
												<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
											</select>
										</td>
										<th><span class="required">*</span> 사용원인</th>
										<td colspan="3">
											<select name="useCauCd" style="width: 150px;">
												<option value="">선택</option>
<c:forEach var="result" items="${useCauCdList}" varStatus="status">
												<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
											</select>
											<div class="dsp-ibk"><form:input path="cslAssInfo.useCauEtc" cssClass="el-input__inner" placeholder="기타 선택시 입력 가능" disabled="true" style="width: 150px;" /></div>
										</td>
									</tr>
									<tr>
										<th><span class="required">*</span> 약물관련 법적문제</th>
										<td colspan="5">
											<select name="lawPbmCd" id="lawPbmCd" style="width: 150px;" multiple>
<c:forEach var="result" items="${lawPbmCdList}" varStatus="status">
												<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
											</select>
											<div class="dsp-ibk"><form:input path="cslAssInfo.lawPbmEtc" cssClass="el-input__inner" disabled="true" placeholder="기타 선택시 입력 가능" style="width: 432px;" /></div>
										</td>
									</tr>
									<tr>
										<th><span class="required">*</span> 신체적 건강문제</th>
										<td colspan="5"><form:input path="cslAssInfo.physPbm" cssClas="el-input__inner" placeholder="신체적 건강문제" style="width: 100%;" /></td>
									</tr>
									<tr>
										<th><span class="required">*</span> 정신적 건강문제</th>
										<td colspan="5">
											<select name="sprtPbmCd" style="width: 150px;">
												<option value="">선택</option>
<c:forEach var="result" items="${sprtPbmCdList}" varStatus="status">
												<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
											</select>
											<span class="dsp-ibk"><form:input path="cslAssInfo.sprtPbmEtc" cssClass="el-input__inner" disabled="true" placeholder="기타 선택시 입력 가능" style="width: 432px;" /></span>
										</td>
									</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="row2">
						<div class="section pdn" style="height: 407px;">
							<div class="el-card_header"><h2><i class="el-icon-s-opportunity"></i> 치료정보</h2></div>
							<div class="el-card_body">
								<table class="w-auto wr-form">
									<tbody>
									<tr>
										<th>성격</th>
										<td>
											<select name="prsnCd" id="prsnCd" style="width: 220px;" multiple>
												<option value="">선택</option>
<c:forEach var="result" items="${prsnCdList}" varStatus="status">
												<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
											</select>
										</td>
										<th>정서-심리</th>
										<td colspan="3">
											<select name="emtnCd" id="emtnCd" style="width: 170px;" multiple>
												<option value="">선택</option>
<c:forEach var="result" items="${emtnCdList}" varStatus="status">
												<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<th>행동</th>
										<td>
											<select name="actnCd" id="actnCd" style="width: 170px;" multiple>
												<option value="">선택</option>
<c:forEach var="result" items="${actnCdList}" varStatus="status">
												<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
											</select>
										</td>
										<th>가족</th>
										<td>
											<select name="fmlyCd" id="fmlyCd" style="width: 170px;" multiple>
												<option value="">선택</option>
<c:forEach var="result" items="${fmlyCdList}" varStatus="status">
												<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
											</select>
										</td>
										<th>대인관계</th>
										<td>
											<select name="itRlCd" id="itRlCd" style="width: 170px;" multiple>
												<option value="">선택</option>
<c:forEach var="result" items="${itRlCdList}" varStatus="status">
												<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<th>기타</th>
										<td colspan="3"><form:input path="cslAssInfo.miEtc" cssClass="el-input__inner" placeholder="기타" style="width: 100%;" /></td>
										<th>심각성인식정도</th>
										<td>
											<div class="count-number">
												<form:input path="cslAssInfo.svrRcgDgr" cssClass="el-input__inner" style="width: 80px;" maxVal="10" minVal="1" />
												<a href="javaScript:tagNumChang('svrRcgDgr', +1);" class="up"><i class="el-icon-arrow-up"></i></a>
												<a href="javaScript:tagNumChang('svrRcgDgr', -1);" class="down"><i class="el-icon-arrow-down"></i></a>
											</div>
										</td>
									</tr>
									<tr>
										<th>기대효과</th>
										<td colspan="5">
											<textarea name="expEfc" placeholder="기대효과" style="width:367px;height: 220px;"></textarea>
											<span style="display:inline-block;padding:0 7px;font-size:14px;color:#333;font-weight:400">메모</span>
											<textarea name="miMemo" placeholder="메모" style="width:367px;height: 220px;"></textarea>
										</td>
									</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="section pdn el-row">
					<div class="el-card_header">
						<h2><i class="el-icon-s-opportunity"></i> 평가도구</h2>
					</div>
					<div class="el-card_body el-row">
						<div class="sec-inr">
							<div class="table-box">
								<div class="el-table_header-wrapper">
									<table>
										<colgroup>
											<col style="width:46px">
											<col style="width:90px">
											<col style="width:120px">
											<col style="width:100px">
											<col>
										</colgroup>
										<thead>
										<tr>
											<th>#</th>
											<th>작업</th>
											<th>평가 도구</th>
											<th>평가 점수</th>
											<th>평가 내용</th>
										</tr>
										</thead>
									</table>
								</div>
								<div class="el-table_body-wrapper" style="height: 110px;" id="assEvlList">
									<div class="no-data">조회된 데이터가 없습니다.</div>
								</div>
							</div>
						</div>
						<div class="cn-arw">
							<button type="button" onclick="javaScript:addRowEval();" class="el-button el-button--primary el-button--mini is-plain">
								<span>◀◀<br><br>행 추가</span>
							</button>
						</div>
						<div class="sec-inr">
							<div class="section mt0">
								<table class="w-auto wr-form">
									<tbody>
									<tr>
										<th><span class="required">*</span> 평가도구</th>
										<td>
											<select name="evlTolCd" style="width:170px">
												<option value="">평가도구</option>
<c:forEach var="result" items="${evlTolCdList}" varStatus="status">
												<option value="<c:out value="${result.CD_ID}"/>"><c:out value="${result.CD_NM}" /></option>
</c:forEach>
											</select>
										</td>
										<th><span class="required">*</span> 평가점수</th>
										<td><input name="evlSco" type="text" placeholder="평가점수" style="width:170px" /></td>
									</tr>
									</tbody>
								</table>
								<table class="wr-form sig-form">
									<colgroup>
										<col style="width: 85px;">
										<col>
									</colgroup>
									<tbody>
									<tr>
										<th><span class="required">*</span> 평가내용</th>
										<td><textarea name="evlCtnt" style="height: 86px;" placeholder="평가 내용을 입력하세요."></textarea></td>
									</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div></div>
			<!-- // 사정평가 -->
		</div>
	</div>
	<!-- // 집중상담, ISP수립, 사정평가 -->
</div>
</form>

<!-- 상담내용 팝업 -->
<div class="layerpopup" data-popup="counwrite">
	<input type="hidden" name="tagName" />
	<div class="popup">
		<div class="pop-header">
			<span>상세내용</span>
			<button type="button" class="el-dialog__headerbtn" onclick="javaScript:layerPopupClose('counwrite');">
				<i class="el-dialog__close el-icon el-icon-close"></i>
			</button>
		</div>
		<div class="pop-content">
			<div class="section bg">
				<textarea name="viewCslCtnt" style="height: 430px;" placeholder="상세내용을 입력하세요."></textarea>
			</div>
			<!-- 닫기 -->
			<div class="el-dialog__footer">
				<button type="button" onclick="javaScript:setCslCtnt();" class="el-button el-button--primary el-button--small is-plain">
					<i class="el-icon-check"></i> <span>확인</span>
				</button>
				<button type="button" onclick="javaScript:layerPopupClose('counwrite');" class="el-button el-button--default el-button--small">
					<i class="el-icon-close"></i> <span>닫기</span>
				</button>
			</div>
			<!-- // 닫기 -->
		</div>
	</div>
</div>
<!-- // 상담내용 팝업 -->