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
    for(var i = 0; i < f.elements.length; i++){
        if(f.elements[i].type == 'checkbox'){
            f.elements[i].checked = check;
        }
    }
}

function checkSelected(value){
    var form = value.form;
    var status = false;
    for (var i = 0; i < form.elements.length; i++){
        if (form.elements[i].type == 'checkbox'){
            if (form.elements[i].checked){
                status = true;
                return true;
            }
        }
    }
    if (status == false){
        alert("Please Select Song");
        return false;
    }
}



