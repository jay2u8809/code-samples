import dayjs from 'dayjs';

// npm i dayjs
test('dayjs test',  () => {

  console.log(`Today(Date) : ${new Date().toISOString()}`); // 2001-01-01T01:06:33.018Z

  // Current Time
  const today = dayjs();
  console.log(`Today(DayJs) : ${today.toISOString()}`); // 2021-01-01T01:06:33.046Z

  // Basic Format
  console.log(`Basic Format : ${dayjs().format()}`);  // 2021-01-01T10:06:33+09:00

  // Format
  console.log(`Format, YYYY-MM-DD, hh:mm:ss ddd : ${dayjs().format('YYYY-MM-DD, hh:mm:ss ddd')}`);
  console.log(`Format DateType : ${dayjs(new Date()).format()}`);

  console.log(`-30 days : ${today.subtract(30, 'd').toISOString()}`);
  console.log(`-1 Month : ${today.subtract(1, 'M').toISOString()}`);
});
