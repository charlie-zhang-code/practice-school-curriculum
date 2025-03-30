## Data Preparation

Create data table named user_info

```bash
create 'user_info', 'basic', 'extend', 'time_sign', 'time_data'
```

Insert some data into the user_info data table

```bash
put 'user_info', 'u781078001', 'basic:name','Alice'
put 'user_info', 'u781078001', 'basic:age','18'
put 'user_info', 'u781078001', 'basic:gender','female'
put 'user_info', 'u781078001', 'basic:id_card','80'
put 'user_info', 'u781078001', 'basic:email', 'alice@alc.com'
put 'user_info', 'u781078001', 'extend:wechat','9090sa0745a64ghwsrga6'
put 'user_info', 'u781078001', 'extend:phone', '123-456-7890'
put 'user_info', 'u781078001', 'time_sign:in', '2025-01-01 15:00'
put 'user_info', 'u781078001', 'time_sign:out', '2025-01-01 17:50'
put 'user_info', 'u781078001', 'time_data:edit', '2024-01-01 18:50'
put 'user_info', 'u781078001', 'time_data:block', '2024-12-01 11:30'

put 'user_info', 'u781078002', 'basic:name', 'Bob'
put 'user_info', 'u781078002', 'basic:gender', 'male'
put 'user_info', 'u781078002', 'basic:id_card', '90'
put 'user_info', 'u781078002', 'basic:email', 'bob@example.com'
put 'user_info', 'u781078002', 'extend:wechat', '1234567890'
put 'user_info', 'u781078002', 'extend:phone', '987-654-3210'

put 'user_info', 'u781078003', 'basic:name', 'Charlie'
put 'user_info', 'u781078003', 'basic:age', '30'
put 'user_info', 'u781078003', 'basic:id_card', '85'
put 'user_info', 'u781078003', 'basic:email', 'charlie@example.com'
put 'user_info', 'u781078003', 'extend:wechat', '0987654321'
put 'user_info', 'u781078003', 'extend:phone', '111-222-3333'
put 'user_info', 'u781078003', 'time_sign:in', '2023-01-01 15:00'
put 'user_info', 'u781078003', 'time_sign:out', '2023-01-01 17:50'
put 'user_info', 'u781078003', 'time_data:edit', '2023-01-01 18:50'
put 'user_info', 'u781078003', 'time_data:block', '2023-12-01 11:30'

put 'user_info', 'u781078004', 'basic:name', 'Diana'
put 'user_info', 'u781078004', 'basic:age', '22'
put 'user_info', 'u781078004', 'basic:gender', 'female'
put 'user_info', 'u781078004', 'basic:email', 'diana@outlook.com'
put 'user_info', 'u781078004', 'extend:phone', '444-555-6666'

put 'user_info', 'u781078005', 'basic:name', 'Edward'
put 'user_info', 'u781078005', 'basic:age', '28'
put 'user_info', 'u781078005', 'basic:gender', 'male'
put 'user_info', 'u781078005', 'basic:email', 'edward@icloud.com'
put 'user_info', 'u781078005', 'extend:wechat', 'edward_8765'
put 'user_info', 'u781078005', 'extend:phone', '777-888-9999'
put 'user_info', 'u781078005', 'time_sign:in', '2022-01-01 15:00'
put 'user_info', 'u781078005', 'time_data:block', '2022-12-01 11:30'

put 'user_info', 'u781078006', 'basic:age', '24'
put 'user_info', 'u781078006', 'basic:gender', 'female'
put 'user_info', 'u781078006', 'basic:id_card', '55'
put 'user_info', 'u781078006', 'basic:email', 'fiona@yahoo.com'
put 'user_info', 'u781078006', 'extend:wechat', 'fiona_abcde'
put 'user_info', 'u781078006', 'extend:phone', '333-444-5555'

put 'user_info', 'u781078007', 'basic:name', 'George'
put 'user_info', 'u781078007', 'basic:gender', 'male'
put 'user_info', 'u781078007', 'basic:id_card', '40'
put 'user_info', 'u781078007', 'basic:email', 'george@hotmail.com'
put 'user_info', 'u781078007', 'extend:wechat', 'george_xyz12'
put 'user_info', 'u781078007', 'extend:phone', '222-333-4444'

put 'user_info', 'u781078008', 'basic:name', 'Hannah'
put 'user_info', 'u781078008', 'basic:age', '27'
put 'user_info', 'u781078008', 'basic:gender', 'female'
put 'user_info', 'u781078008', 'basic:id_card', '30'
put 'user_info', 'u781078008', 'extend:wechat', 'hannah_54321'
put 'user_info', 'u781078008', 'extend:phone', '111-333-5555'
put 'user_info', 'u781078008', 'time_data:edit', '2012-01-01 18:50'
put 'user_info', 'u781078008', 'time_data:block', '2021-12-01 11:30'

put 'user_info', 'u781078009', 'basic:name', 'Ian'
put 'user_info', 'u781078009', 'basic:age', '31'
put 'user_info', 'u781078009', 'basic:gender', 'male'
put 'user_info', 'u781078009', 'basic:id_card', '25'
put 'user_info', 'u781078009', 'basic:email', 'ian@live.com'
put 'user_info', 'u781078009', 'extend:wechat', 'ian_wechat007'
put 'user_info', 'u781078009', 'extend:phone', '666-777-8888'

put 'user_info', 'u781078010', 'basic:name', 'Jane'
put 'user_info', 'u781078010', 'basic:age', '29'
put 'user_info', 'u781078010', 'basic:gender', 'female'
put 'user_info', 'u781078010', 'basic:id_card', '15'
put 'user_info', 'u781078010', 'basic:email', 'jane@outlook.com'
put 'user_info', 'u781078010', 'extend:wechat', 'jane_smith123'

put 'user_info', 'u781078011', 'basic:name', 'Jack'
put 'user_info', 'u781078011', 'basic:age', '33'
put 'user_info', 'u781078011', 'basic:gender', 'male'
put 'user_info', 'u781078011', 'basic:id_card', '35'
put 'user_info', 'u781078011', 'basic:email', 'jack@gmail.com'
put 'user_info', 'u781078011', 'extend:wechat', 'jack_wechat'
put 'user_info', 'u781078011', 'extend:phone', '555-666-7777'

put 'user_info', 'u781078012', 'basic:name', 'Kelly'
put 'user_info', 'u781078012', 'basic:age', '26'
put 'user_info', 'u781078012', 'basic:gender', 'female'
put 'user_info', 'u781078012', 'basic:id_card', '45'
put 'user_info', 'u781078012', 'basic:email', 'kelly@yahoo.com'
put 'user_info', 'u781078012', 'extend:wechat', 'kelly_123'
put 'user_info', 'u781078012', 'extend:phone', '888-999-0000'

put 'user_info', 'u781078013', 'basic:name', 'Liam'
put 'user_info', 'u781078013', 'extend:phone', '777-888-9999'

put 'user_info', 'u781078014', 'basic:name', 'Mia'
put 'user_info', 'u781078014', 'basic:age', '24'
put 'user_info', 'u781078014', 'basic:gender', 'female'
put 'user_info', 'u781078014', 'basic:id_card', '40'
put 'user_info', 'u781078014', 'basic:email', 'mia@gmail.com'
put 'user_info', 'u781078014', 'extend:wechat', 'mia_123'
put 'user_info', 'u781078014', 'extend:phone', '666-777-8888'

put 'user_info', 'u781078015', 'basic:name', 'Noah'
put 'user_info', 'u781078015', 'basic:age', '30'
put 'user_info', 'u781078015', 'basic:id_card', '55'
put 'user_info', 'u781078015', 'extend:phone', '999-888-7777'
```

```bash
scan 'user_info'
```

## Individual Filter Syntax Demo

### KeyOnlyFilter

```bash
scan 'user_info', FILTER=>"KeyOnlyFilter()"
```

### FirstKeyOnlyFilter

```bash
scan 'user_info', FILTER=>"FirstKeyOnlyFilter()"
```

### PrefixFilter

```bash
scan 'user_info', FILTER=>"PrefixFilter('u781078001')"
```

```bash
scan 'user_info', FILTER=>"PrefixFilter('u78107801')"
```

### ColumnPrefixFilter

```bash
scan 'user_info', FILTER=>"ColumnPrefixFilter('name')"
```

```bash
scan 'user_info', FILTER=>"ColumnPrefixFilter('edit')"
```

### MultipleColumnPrefixFilter

```bash
scan 'user_info', FILTER=>"MultipleColumnPrefixFilter('name', 'edit')"
```

### ColumnCountGetFilter

```bash
get 'user_info', 'u781078001', FILTER=>"ColumnCountGetFilter(3)"
```

### PageFilter

```bash
scan 'user_info', FILTER=>"PageFilter(2)"
```

### ColumnPaginationFilter

```bash
scan 'user_info', FILTER=>"ColumnPaginationFilter(2, 1)"
```

### InclusiveStopFilter

```bash
scan 'user_info', STARTROW=>'u781078002', STOPROW=>'u781078004', FILTER=>"InclusiveStopFilter('u781078003')"
```

### TimestampsFilter

```bash
scan 'user_info', FILTER=>"TimestampsFilter(1647283200000, 1647286800000)"
```

### RowFilter

```bash
scan 'user_info', FILTER=>"RowFilter(CompareFilter::EQUAL, SubstringComparator('u781078001'))"
```

### FamilyFilter

```bash
scan 'user_info', FILTER=>"FamilyFilter(CompareFilter::EQUAL, SubstringComparator('basic'))"
```

### QualifierFilter

```bash
scan 'user_info', FILTER=>"QualifierFilter(CompareFilter::EQUAL, SubstringComparator('name'))"
```

### ValueFilter

```bash
scan 'user_info', FILTER=>"ValueFilter(CompareFilter::EQUAL, SubstringComparator('Alice'))"
```

### DependentColumnFilter

```bash
scan 'user_info', FILTER=>"DependentColumnFilter('basic', 'name', CompareFilter::EQUAL, SubstringComparator('Alice'))"
```

### SingleColumnValueFilter

```bash
scan 'user_info', FILTER=>"SingleColumnValueFilter('basic', 'name', CompareFilter::EQUAL, 'Alice')"
```

### SingleColumnValueExcludeFilter

```bash
scan 'user_info', FILTER=>"SingleColumnValueExcludeFilter('basic', 'name', CompareFilter::EQUAL, 'Alice')"
```

### ColumnRangeFilter

```bash
scan 'user_info', FILTER=>"ColumnRangeFilter('basic:id_card', true, 'basic:email', false)"
```

```bash
disable 'user_info'
is_enabled 'user_info'
drop 'user_info'
```