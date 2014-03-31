[~, ~, raw] = xlsread('fromPaper.xlsx');
airports = raw(2:end, 2);
[~, ~, raw] = xlsread('flightData.csv');
[r, c] = size(raw);
%this loop goes through and deletes any row whose origin or dest airport is
%not on the list
for i = r:-1:2
   if ~any(strcmp(airports, raw(i, 10))) | ~any(strcmp(airports, raw(i, 15)))
       raw(i, :) = [];
   end    
end
%just gonna go ahead and save that... it only took like 12 hours to run...
xlswrite('editedData.xlsx', raw)

%make adj matrix. "from" down the rows. "to" across the cols
% adjMat = cell(47, 47);
% adjMat(2:end, 1) = airports;
% adjMat(1, 2:end) = airports;
% adjMat(2:end, 2:end) = num2cell(zeros(46,46));

adjMat = zeros(47, 47); %don't mess with first row/col.

[r,c] = size(raw);
for i = 2:r
   row = find(strcmp(airports, raw(i, 10)));
   col = find(strcmp(airports, raw(i, 15)));
   if (row == col)
       fprintf('city: %s, row: %d\n', raw{i, 10}, i);
   end
   adjMat(row+1, col+1) = adjMat(row+1, col+1) + 1;
end

adjMat = num2cell(adjMat);
adjMat(2:end, 1) = airports;
adjMat(1, 2:end) = airports;

xlswrite('initialMatrix.xlsx', adjMat)