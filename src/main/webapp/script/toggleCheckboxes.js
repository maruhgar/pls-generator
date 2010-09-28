/*
 * Copyright 2010 Raghuram
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

function toggleCheckboxes(chkBx){
    var f = chkBx.form;
    var check = chkBx.checked;
    for(var i = 0;i<f.elements.length;i++){
        if(f.elements[i].type == 'checkbox'){
            f.elements[i].checked = check;
        }
    }
}

function checkSelected(value){
var form = value.form;
var status=false;
      for(var i = 0;i<form.elements.length;i++){

            if(form.elements[i].type == 'checkbox'){

                  if(form.elements[i].checked){

                        status=true;

                        return true;

                  }

            }

      }

      if(status==false){

            alert("Please Select Song");
             return false;
      }

}

function toggleCheckboxesDiv(chkBx){
    var f = chkBx.form;
    var check = chkBx.checked;
    for(var i = 0;i<f.elements.length;i++){
        if(f.elements[i].type == 'checkbox'){
            f.elements[i].checked = check;
        }
    }
}

function unToggleCheckbox(chkBx,gropChkb,chkName){
    var f = chkBx.form; // form
    var check = chkBx.checked;
    var flag=true;
    if ("undefined" == typeof(f.elements[chkName].length) ) {
        if(!f.elements[chkName].checked){
            gropChkb.checked=false;
            flag=false;
        }
    }else{
        for(var i = 0;i<f.elements[chkName].length;i++){
            if(!(f.elements[chkName][i].checked)){
                gropChkb.checked=false;
                flag=false;
                break;
            }
        }
    }

    if(flag){
        gropChkb.checked=true;
    }
}

function changeTableBackground(chk) {

tab = document.getElementById("table");
if(chk.checked){
    for(var i=1;i<tab.rows.length;i++)
    for(var j=0;j<tab.rows[i].cells.length;j++)
    if(tab.rows[i].cells[tab.rows[i].cells.length-1].innerHTML.indexOf("Checkbox")>-1)
    tab.rows[i].cells[j].style.background="#99ff99";
 }else{
 var flag=1;
 for(var i=1;i<tab.rows.length;i=i+1){
    if(flag==1){
    for(var j=0;j<tab.rows[i].cells.length;j++)
    if(tab.rows[i].cells[tab.rows[i].cells.length-1].innerHTML.indexOf("Checkbox")>-1)
        tab.rows[i].cells[j].style.background="#DDDDDD";
        flag=0;
        continue;
    }
    if(flag==0){
    for(var j=0;j<tab.rows[i].cells.length;j++)
    if(tab.rows[i].cells[tab.rows[i].cells.length-1].innerHTML.indexOf("Checkbox")>-1)
        tab.rows[i].cells[j].style.background="#FFFFFF";
        flag=1;
        continue;
    }
    }
 }

}


