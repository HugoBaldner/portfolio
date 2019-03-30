
function  demodulator(FileName)
[x,Fs] = audioread(strcat(FileName,'.wav'));
y = resample(x,16640,Fs);
% squared signal added imaginary part of 
% hilbert singal squared multiply by 1/2 sqrt of all that
w = sqrt(.5*(y.^2 + imag( hilbert(y)).^2));
lw = lowpass(w,2400/16640,16000);
z = decimate(lw,4);

cross = z(1:2080,1);
syncAlpha = [0;0;0;0;1;1;0;0;1;1;0;0;1;1;0;0;1;1;0;0;1;1;0;0;1;1;0;0;1;1;0;0;0;0;0;0;0;0;0];

[r,lags] = xcorr(cross,syncAlpha);

for i = 1:size(r)
   if r(i,1) == max(r)
       start = i-39;
       
   end
    
end
z = z(start:size(z),1);
z = im2uint8(z);
row = floor(size(z)/2080);

row = row(1);
current = 1;
for i = 1:row
    for j = 1:2080
        
        image(i,j) = z(current,1);
        current = current + 1;
    end

end

imwrite(image,strcat(FileName,'.jpg'));
