% Thomas Bowling
% CX 4230
% Project Two
% Airplane logo manipulation

A = imread('air_plane.jpg');

R = A(:,:,1);
G = A(:,:,2);
B = A(:,:,3);
alpha = zeros(size(B));

value = 220;
inds = find(R<value | G < value | B < value);
%% Cyan
R(inds) = 107;
G(inds) = 227;
B(inds) = 221;
alpha(inds) = 1;

newA = R;
newA(:,:,2) = G;
newA(:,:,3) = B;

%% Green
R(inds) = 82;
G(inds) = 222;
B(inds) = 98;

newA2 = R;
newA2(:,:,2) = G;
newA2(:,:,3) = B;

%% 3: Blue
R(inds) = 74;
G(inds) = 113;
B(inds) = 231;

newA3 = R;
newA3(:,:,2) = G;
newA3(:,:,3) = B;

%% 4: Purple
R(inds) = 142;
G(inds) = 74;
B(inds) = 231;

newA4 = R;
newA4(:,:,2) = G;
newA4(:,:,3) = B;

%% Show
figure()
subplot(1,5,1);
imshow(A);
subplot(1,5,2);
imshow(newA);
subplot(1,5,3);
imshow(newA2);
subplot(1,5,4);
imshow(newA3);
subplot(1,5,5);
imshow(newA4);

%% Save
imwrite(newA, 'air_plane_cyan.png','png', 'Alpha', alpha);
imwrite(newA2, 'air_plane_green.png','png', 'Alpha', alpha);
imwrite(newA3, 'air_plane_blue.png','png', 'Alpha', alpha);
imwrite(newA4, 'air_plane_purple.png','png', 'Alpha', alpha);