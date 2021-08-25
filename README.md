# Find a total ordered quantity of a product for every weekday

1. Implement `OrdersAnalyzer#totalDailySales` method that for passed orders will return a map with a DayOfWeek as a key and a sum of ordered quantity for a given product id as a value. 

2. The method should always return a map. If there are no orders for passed `productId` for some weekday, there might be no entry in the resulting map or there might be an entry with a `0` value. 

## Example output

Your implementation should return following output for **productId=9872** for example data:

```json
{
  DayOfWeek.MONDAY : 3, 
  DayOfWeek.TUESDAY : 0, 
  DayOfWeek.WEDNESDAY : 0, 
  DayOfWeek.THURSDAY : 0, 
  DayOfWeek.FRIDAY  : 0, 
  DayOfWeek.SATURDAY : 5,
  DayOfWeek.SUNDAY : 4 
}
```

In the example above, there are two orders placed on Saturday: first with quantity equal to `3` and second with quantity equal to `2`, so the sum of quantities is `5`. For Monday there is only one order for this product, so total quantity is equal to `3`, and for Sunday it's `4`.

## Example input

Orders collection:
```javascript
[
    {
        orderId: 554,
        creationDate: "2017-03-25T10:35:20", // Saturday
        orderLines: [
            {productId: 9872, name: 'Pencil', quantity: 3, unitPrice: 3.00}
        ]
    },
    {
        orderId: 555,
        creationDate: "2017-03-25T11:24:20", // Saturday
        orderLines: [
            {productId: 9872, name: 'Pencil', quantity: 2, unitPrice: 3.00},
            {productId: 1746, name: 'Eraser', quantity: 1, unitPrice: 1.00}
        ]
    },
    {
        orderId: 453,
        creationDate: "2017-03-27T14:53:12", // Monday
        orderLines: [
            {productId: 5723, name: 'Pen', quantity: 4, unitPrice: 4.22},
            {productId: 9872, name: 'Pencil', quantity: 3, unitPrice: 3.12},
            {productId: 3433, name: 'Erasers Set', quantity: 1, unitPrice: 6.15}
        ]
    },
    {
        orderId: 431,
        creationDate: "2017-03-20T12:15:02", // Monday
        orderLines: [
            {productId: 5723, name: 'Pen', quantity: 7, unitPrice: 4.22},
            {productId: 3433, name: 'Erasers Set', quantity: 2, unitPrice: 6.15}
        ]
    },
    {
        orderId: 690,
        creationDate: "2017-03-26T11:14:00", // Sunday
        orderLines: [
            {productId: 9872, name: 'Pencil', quantity: 4, unitPrice: 3.12},
            {productId: 4098, name: 'Marker', quantity: 5, unitPrice: 4.50}
        ]
    }
];
```