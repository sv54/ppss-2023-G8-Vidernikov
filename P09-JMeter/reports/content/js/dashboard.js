/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 100.0, "KoPercent": 0.0};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [1.0, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [1.0, 500, 1500, "Reptiles"], "isController": false}, {"data": [1.0, 500, 1500, "Dogs"], "isController": false}, {"data": [1.0, 500, 1500, "Main Catalog"], "isController": false}, {"data": [1.0, 500, 1500, "Sing In"], "isController": false}, {"data": [1.0, 500, 1500, "Birds"], "isController": false}, {"data": [1.0, 500, 1500, "Login"], "isController": false}, {"data": [1.0, 500, 1500, "Login-0"], "isController": false}, {"data": [1.0, 500, 1500, "Home"], "isController": false}, {"data": [1.0, 500, 1500, "Login-1"], "isController": false}, {"data": [1.0, 500, 1500, "Sing Out"], "isController": false}, {"data": [1.0, 500, 1500, "Sing Out-1"], "isController": false}, {"data": [1.0, 500, 1500, "Sing Out-0"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 1493, 0, 0.0, 3.006697923643671, 0, 448, 2.0, 4.0, 6.0, 14.059999999999945, 20.323155874385748, 69.76017848695943, 13.654567180077047], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["Reptiles", 50, 0, 0.0, 2.1399999999999992, 1, 5, 2.0, 3.0, 4.449999999999996, 5.0, 1.1297903109182936, 4.095489877078814, 0.2780343343275488], "isController": false}, {"data": ["Dogs", 50, 0, 0.0, 2.7600000000000007, 1, 13, 2.0, 4.899999999999999, 8.899999999999991, 13.0, 1.1297647829721853, 4.739716316062996, 0.2736149083760761], "isController": false}, {"data": ["Main Catalog", 159, 0, 0.0, 1.8176100628930825, 0, 17, 2.0, 2.0, 3.0, 12.200000000000045, 2.231140547822182, 10.846799620074652, 1.0897529783621465], "isController": false}, {"data": ["Sing In", 156, 0, 0.0, 2.1923076923076925, 1, 12, 2.0, 3.0, 4.150000000000006, 10.860000000000014, 2.2167592684694415, 8.492414970940558, 1.3097064037343866], "isController": false}, {"data": ["Birds", 45, 0, 0.0, 2.955555555555555, 1, 19, 2.0, 4.399999999999999, 10.0, 19.0, 1.0621474260627375, 3.848209912785895, 0.2582760830953337], "isController": false}, {"data": ["Login", 149, 0, 0.0, 7.382550335570472, 2, 448, 3.0, 6.0, 12.5, 261.5, 2.2450240323042383, 11.536441853500882, 3.56266020751405], "isController": false}, {"data": ["Login-0", 149, 0, 0.0, 2.046979865771812, 1, 10, 2.0, 3.0, 5.5, 10.0, 2.2450578592092576, 0.42314078791736986, 2.0608929566959984], "isController": false}, {"data": ["Home", 163, 0, 0.0, 1.220858895705522, 0, 7, 1.0, 2.0, 3.0, 6.359999999999985, 2.238580492762381, 2.953439693087868, 0.40534304443513614], "isController": false}, {"data": ["Login-1", 149, 0, 0.0, 5.208053691275169, 0, 446, 1.0, 3.0, 4.5, 259.5, 2.2450578592092576, 11.113474890948952, 1.5018209312093178], "isController": false}, {"data": ["Sing Out", 141, 0, 0.0, 4.0, 1, 20, 3.0, 7.0, 11.700000000000017, 19.160000000000025, 2.2358593787165213, 11.52609481292517, 2.342941656557728], "isController": false}, {"data": ["Sing Out-1", 141, 0, 0.0, 1.8439716312056735, 0, 12, 1.0, 4.0, 5.0, 11.580000000000013, 2.235930289719478, 10.799346317454528, 1.1595450486433772], "isController": false}, {"data": ["Sing Out-0", 141, 0, 0.0, 1.9574468085106382, 0, 15, 1.0, 3.0, 5.900000000000006, 14.580000000000013, 2.235894833655767, 0.7271025191478228, 1.1834521482826426], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": []}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 1493, 0, "", "", "", "", "", "", "", "", "", ""], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
