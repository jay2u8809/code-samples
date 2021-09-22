import dayjs from 'dayjs';

test('dayjs test',  () => {
  console.log(`Today(Date) : ${new Date().toISOString()}`);
  const today = dayjs();
  console.log(`Today(DayJs) : ${today.toISOString()}`);
  console.log(`-30 days : ${today.subtract(30, 'd').toISOString()}`);
  console.log(`-1 Month : ${today.subtract(1, 'M').toISOString()}`);
});
