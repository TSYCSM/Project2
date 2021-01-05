//막대 그래프
function drawStacked() {
      var data = new google.visualization.DataTable();
      data.addColumn('date', '날짜');
      data.addColumn('number', 'box1');
      data.addColumn('number', 'box2');

      data.addRows([
        [new Date(2020, 11, 23), 1, 7],
        [new Date(2020, 11, 24), 2, 5],
        [new Date(2020, 11, 25), 3, 1]
      ]);

      var options = {
        title: 'Motivation and Energy Level Throughout the Day',
        isStacked: true,
        hAxis: {
          title: 'Time of Day',
          format: 'MMM d, y'
        },
        vAxis: {
          title: 'Rating (scale of 1-10)'
        }
      };

      var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
      chart.draw(data, options);
    }

//꺾인 그래프
     function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['date', 'Sales'],
          ['2020.12.17',  0],
          ['2020.12.18',  40],
          ['2020.12.19',  20],
          ['2020.12.20',  25]
        ]);

        var options = {
          title: 'sales gross',
          hAxis: {title: 'date',  titleTextStyle: {color: '#333'}},
          vAxis: {minValue: 0}
        };

        var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }






