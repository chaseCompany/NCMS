<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 페이지 타이틀 -->
<div class="tit-area">
    <h1>
        <i class="el-icon-s-order" style="color: rgb(0, 108, 185);"></i>
        사용자 관리
    </h1>
</div>
<!-- // 페이지 타이틀 -->

<div class="formline">
   
    <!-- 사용자 목록, 사용자 정보 -->
    <div class="el-row">
        <div class="row2">
            <div class="top-right-btn">
                <button type="button" class="el-button el-button--primary el-button--small is-plain" style="margin-left: 8px;">
                    <i class="el-icon-refresh-right"></i><span>초기화</span>
                </button>
            </div>
            <div class="section pdn mgn">
                <div class="el-card_header">
                    <h2>
                        <i class="el-icon-s-opportunity"></i>
                        사용자 목록
                    </h2>
                </div>
                <div class="el-card_body">

                    <!-- 검색 -->
                    <div class="section bg-sky">
                        <table class="w-auto">
                            <tbody>
                                <tr>
                                    <th>ID</th>
                                    <td>
                                        <input type="text" class="el-input__inner" style="width:100px">
                                    </td>
                                    <th>성명</th>
                                    <td>
                                        <input type="text" class="el-input__inner" style="width:100px">
                                    </td>
                                    <th>권한</th>
                                    <td>
                                        <select name="" id="" style="width:100px">
                                            <option value="">선택</option>
                                        </select>
                                    </td>
                                    <th>소속 본부</th>
                                    <td>
                                        <select name="" id="" style="width:120px">
                                            <option value="">소속 본부</option>
                                        </select>
                                    </td>
                                    <th>사용 여부</th>
                                    <td>
                                        <select name="" id="" style="width:100px">
                                            <option value="">선택</option>
                                        </select>
                                        <button type="button" class="el-button el-button--primary el-button--small is-plain" style="margin-left: 8px;">
                                            <i class="el-icon-search"></i><span>검색</span>
                                        </button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <!-- // 검색 -->

                    <!-- 검색 결과 -->
                    <div class="table-box mgt10">
                        <div class="el-table_header-wrapper">
                            <table>
                                <colgroup>
                                    <col style="width:46px">
                                    <col style="width:120px">
                                    <col style="width:160px">
                                    <col style="width:200px">
                                    <col style="width:120px">
                                    <col style="width:80px">
                                    <col>
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>사용자ID</th>
                                        <th>사용자명</th>
                                        <th>소속 본부</th>
                                        <th>사용 권한</th>
                                        <th>사용</th>
                                        <th>등록일시</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                        <div class="el-table_body-wrapper" style="height: 540px;">
                            <table>
                                <colgroup>
                                    <col style="width:46px">
                                    <col style="width:120px">
                                    <col style="width:160px">
                                    <col style="width:200px">
                                    <col style="width:120px">
                                    <col style="width:80px">
                                    <col>
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <td>
                                            <div class="cell">1</div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="#" class="row_link">admin</a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cell">관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">본부(센터)</div>
                                        </td>
                                        <td>
                                            <div class="cell">시스템 관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">예</div>
                                        </td>
                                        <td>
                                            <div class="cell">2020-03-18 21:12:23</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="cell">1</div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="#" class="row_link">admin</a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cell">관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">본부(센터)</div>
                                        </td>
                                        <td>
                                            <div class="cell">시스템 관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">예</div>
                                        </td>
                                        <td>
                                            <div class="cell">2020-03-18 21:12:23</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="cell">1</div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="#" class="row_link">admin</a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cell">관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">본부(센터)</div>
                                        </td>
                                        <td>
                                            <div class="cell">시스템 관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">예</div>
                                        </td>
                                        <td>
                                            <div class="cell">2020-03-18 21:12:23</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="cell">1</div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="#" class="row_link">admin</a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cell">관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">본부(센터)</div>
                                        </td>
                                        <td>
                                            <div class="cell">시스템 관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">예</div>
                                        </td>
                                        <td>
                                            <div class="cell">2020-03-18 21:12:23</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="cell">1</div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="#" class="row_link">admin</a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cell">관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">본부(센터)</div>
                                        </td>
                                        <td>
                                            <div class="cell">시스템 관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">예</div>
                                        </td>
                                        <td>
                                            <div class="cell">2020-03-18 21:12:23</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="cell">1</div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="#" class="row_link">admin</a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cell">관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">본부(센터)</div>
                                        </td>
                                        <td>
                                            <div class="cell">시스템 관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">예</div>
                                        </td>
                                        <td>
                                            <div class="cell">2020-03-18 21:12:23</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="cell">1</div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="#" class="row_link">admin</a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cell">관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">본부(센터)</div>
                                        </td>
                                        <td>
                                            <div class="cell">시스템 관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">예</div>
                                        </td>
                                        <td>
                                            <div class="cell">2020-03-18 21:12:23</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="cell">1</div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="#" class="row_link">admin</a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cell">관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">본부(센터)</div>
                                        </td>
                                        <td>
                                            <div class="cell">시스템 관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">예</div>
                                        </td>
                                        <td>
                                            <div class="cell">2020-03-18 21:12:23</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="cell">1</div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="#" class="row_link">admin</a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cell">관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">본부(센터)</div>
                                        </td>
                                        <td>
                                            <div class="cell">시스템 관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">예</div>
                                        </td>
                                        <td>
                                            <div class="cell">2020-03-18 21:12:23</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="cell">1</div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="#" class="row_link">admin</a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cell">관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">본부(센터)</div>
                                        </td>
                                        <td>
                                            <div class="cell">시스템 관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">예</div>
                                        </td>
                                        <td>
                                            <div class="cell">2020-03-18 21:12:23</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="cell">1</div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="#" class="row_link">admin</a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cell">관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">본부(센터)</div>
                                        </td>
                                        <td>
                                            <div class="cell">시스템 관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">예</div>
                                        </td>
                                        <td>
                                            <div class="cell">2020-03-18 21:12:23</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="cell">1</div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="#" class="row_link">admin</a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cell">관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">본부(센터)</div>
                                        </td>
                                        <td>
                                            <div class="cell">시스템 관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">예</div>
                                        </td>
                                        <td>
                                            <div class="cell">2020-03-18 21:12:23</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="cell">1</div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="#" class="row_link">admin</a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cell">관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">본부(센터)</div>
                                        </td>
                                        <td>
                                            <div class="cell">시스템 관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">예</div>
                                        </td>
                                        <td>
                                            <div class="cell">2020-03-18 21:12:23</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="cell">1</div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="#" class="row_link">admin</a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cell">관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">본부(센터)</div>
                                        </td>
                                        <td>
                                            <div class="cell">시스템 관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">예</div>
                                        </td>
                                        <td>
                                            <div class="cell">2020-03-18 21:12:23</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="cell">1</div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="#" class="row_link">admin</a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cell">관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">본부(센터)</div>
                                        </td>
                                        <td>
                                            <div class="cell">시스템 관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">예</div>
                                        </td>
                                        <td>
                                            <div class="cell">2020-03-18 21:12:23</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="cell">1</div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="#" class="row_link">admin</a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cell">관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">본부(센터)</div>
                                        </td>
                                        <td>
                                            <div class="cell">시스템 관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">예</div>
                                        </td>
                                        <td>
                                            <div class="cell">2020-03-18 21:12:23</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="cell">1</div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="#" class="row_link">admin</a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cell">관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">본부(센터)</div>
                                        </td>
                                        <td>
                                            <div class="cell">시스템 관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">예</div>
                                        </td>
                                        <td>
                                            <div class="cell">2020-03-18 21:12:23</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="cell">1</div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="#" class="row_link">admin</a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cell">관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">본부(센터)</div>
                                        </td>
                                        <td>
                                            <div class="cell">시스템 관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">예</div>
                                        </td>
                                        <td>
                                            <div class="cell">2020-03-18 21:12:23</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="cell">1</div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="#" class="row_link">admin</a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cell">관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">본부(센터)</div>
                                        </td>
                                        <td>
                                            <div class="cell">시스템 관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">예</div>
                                        </td>
                                        <td>
                                            <div class="cell">2020-03-18 21:12:23</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="cell">1</div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="#" class="row_link">admin</a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cell">관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">본부(센터)</div>
                                        </td>
                                        <td>
                                            <div class="cell">시스템 관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">예</div>
                                        </td>
                                        <td>
                                            <div class="cell">2020-03-18 21:12:23</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="cell">1</div>
                                        </td>
                                        <td>
                                            <div class="cell">
                                                <a href="#" class="row_link">admin</a>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="cell">관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">본부(센터)</div>
                                        </td>
                                        <td>
                                            <div class="cell">시스템 관리자</div>
                                        </td>
                                        <td>
                                            <div class="cell">예</div>
                                        </td>
                                        <td>
                                            <div class="cell">2020-03-18 21:12:23</div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <!-- <div class="no-data">조회된 데이터가 없습니다.</div> -->
                        </div>
                    </div>
                    <!-- // 검색 결과 -->

                </div>
            </div>
        </div>
        <div class="row2">
            <div class="top-right-btn">
                <button type="button" class="el-button el-button--primary el-button--small is-plain">
                    <i class="el-icon-download"></i><span>저장</span>
                </button>
                <button type="button" class="el-button el-button--primary el-button--small is-plain">
                    <i class="el-icon-circle-plus-outline"></i><span>신규</span>
                </button>
                <button disabled="disabled" type="button" class="el-button el-button--primary el-button--small is-disabled is-plain">
                    <i class="el-icon-delete"></i><span>삭제</span>
                </button>
                <button type="button" class="el-button el-button--primary el-button--small is-plain">
                    <i class="el-icon-delete"></i><span>삭제</span>
                </button>
            </div>
            <div class="section pdn mgn" style="height: 707px;">
                <div class="el-card_header">
                    <h2>
                        <i class="el-icon-s-opportunity"></i>
                        사용자 정보
                    </h2>
                </div>
                <div class="el-card_body">
                    <table class="w-auto wr-form">
                        <tbody>
                            <tr>
                                <th>
                                    <span class="required">*</span>
                                    사용자 ID
                                </th>
                                <td>
                                    <input type="text" class="el-input__inner v-md" readonly style="width: 200px;" placeholder="자동생성(u+지부코드+순번)">
                                    <span class="dsp ibk text">예시) u01001</span>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span class="required">*</span>
                                    사용자 이름
                                </th>
                                <td>
                                    <input type="text" class="el-input__inner" style="width: 200px;" placeholder="사용자 이름">
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span class="required">*</span>
                                    비밀번호
                                </th>
                                <td>
                                    <button disabled="disabled" type="button" class="el-button el-button--danger el-button--small is-disabled is-plain" style="padding: 9px 10px 8px; margin-left: 4px;">
                                        <i class="el-icon-refresh-left"></i><span>비밀번호 초기화</span>
                                    </button>
                                    <button type="button" class="el-button el-button--danger el-button--small is-plain" style="padding: 9px 10px 8px; margin-left: 4px;">
                                        <i class="el-icon-refresh-left"></i><span>비밀번호 초기화</span>
                                    </button>
                                    <div role="alert" class="el-alert el-alert--info is-light mgt10" style="width: 196px;">
                                        <i class="el-alert__icon el-icon-info"></i>
                                        <div class="el-alert__content">
                                            <span class="el-alert__title">초기 비밀번호는 12345</span>
                                            <i class="el-alert__closebtn el-icon-close" style="display: none;"></i>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span class="required">*</span>
                                    소속 본부
                                </th>
                                <td>
                                    <select name="" id="" style="width:200px">
                                        <option value="">본부(센터)</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span class="required">*</span>
                                    사용자 권한
                                </th>
                                <td>
                                    <select name="" id="" style="width:200px">
                                        <option value="">팀원</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span class="required">*</span>
                                    사용 여부
                                </th>
                                <td>
                                    <span class="ck-bx">
                                        <input type="radio" class="el-radio__original" name="ck1" id="ck1-1" checked>
                                        <label for="ck1-1">
                                            <span class="el-radio__input"><span class="el-radio__inner"></span></span>
                                            예
                                        </label>
                                    </span>
                                    <span class="ck-bx">
                                        <input type="radio" class="el-radio__original" name="ck1" id="ck1-2">
                                        <label for="ck1-2">
                                            <span class="el-radio__input"><span class="el-radio__inner"></span></span>
                                            아니요
                                        </label>
                                    </span>
                                </td>
                            </tr>
                            <tr>
                                <th>등록 일시</th>
                                <td>
                                    <input type="text" readonly class="el-input__inner" placeholder="등록 일시" style="width: 200px;">
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <!-- 사용자 목록, 사용자 정보 -->

</div>

